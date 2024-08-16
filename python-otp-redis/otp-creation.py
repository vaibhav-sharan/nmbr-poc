from flask import Flask, request, jsonify
import redis
import secrets
import string
import time
from flask_limiter import Limiter
from flask_limiter.util import get_remote_address

app = Flask(__name__)

# Redis connection
redis_client = redis.StrictRedis(host='localhost', port=6379, db=0, decode_responses=True)

# Rate Limiting
limiter = Limiter(get_remote_address, app=app, default_limits=["5 per minute"])

# Fallback in-memory cache
fallback_cache = {}


def set_otp_fallback(user_id, otp):
    fallback_cache[user_id] = (otp, time.time() + 300)


def get_otp_fallback(user_id):
    otp, expiry = fallback_cache.get(user_id, (None, 0))
    if time.time() > expiry:
        fallback_cache.pop(user_id, None)
        return None
    return otp


# Generate a secure 6-digit OTP
def generate_otp():
    return ''.join(secrets.choice(string.digits) for _ in range(6))


# Endpoint to generate OTP
@app.route('/generate_otp', methods=['POST'])
@limiter.limit("5 per minute")
def generate_otp_endpoint():
    data = request.get_json()
    user_id = data.get('user_id')

    if not user_id:
        return jsonify({'error': 'User identifier is required'}), 400

    otp = generate_otp()
    otp_key = f"otp:{user_id}"

    try:
        # Store OTP in Redis with a TTL of 5 minutes (300 seconds)
        redis_client.setex(otp_key, 300, otp)
    except redis.exceptions.ConnectionError:
        set_otp_fallback(user_id, otp)

    return jsonify({'message': 'OTP generated successfully', 'otp': otp})


# Endpoint to validate OTP
@app.route('/validate_otp', methods=['POST'])
def validate_otp_endpoint():
    data = request.get_json()
    user_id = data.get('user_id')
    otp = data.get('otp')

    if not user_id or not otp:
        return jsonify({'error': 'User identifier and OTP are required'}), 400

    otp_key = f"otp:{user_id}"
    stored_otp = None

    try:
        stored_otp = redis_client.get(otp_key)
    except redis.exceptions.ConnectionError:
        stored_otp = get_otp_fallback(user_id)

    if stored_otp is None:
        return jsonify({'error': 'OTP has expired or is invalid'}), 400

    if stored_otp == otp:
        try:
            redis_client.delete(otp_key)
        except redis.exceptions.ConnectionError:
            fallback_cache.pop(user_id, None)
        return jsonify({'message': 'OTP is valid'})
    else:
        return jsonify({'error': 'OTP is invalid'}), 400


# Error handler
@app.errorhandler(500)
def internal_error(error):
    return jsonify({'error': 'An internal error occurred'}), 500


if __name__ == '__main__':
    app.run(debug=True)
