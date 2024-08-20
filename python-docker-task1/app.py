from flask import Flask, jsonify

app = Flask(__name__)


@app.route('/live')
def live():
    return jsonify({"status": "Running"})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
