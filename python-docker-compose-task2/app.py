from flask import Flask, jsonify
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

# Database configuration
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:postgres@localhost:5432/flask_db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)


# Define the User model
class Users(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    first_name = db.Column(db.String(50), nullable=False)
    phone = db.Column(db.String(20), nullable=False)

    def to_dict(self):
        return {
            "id": self.id,
            "first_name": self.first_name,
            "phone": self.phone
        }


# Route to get user by ID
@app.route('/user/<int:user_id>', methods=['GET'])
def get_user(user_id):
    user = Users.query.get(user_id)
    if user:
        return jsonify({"data": user.to_dict()})
    else:
        return jsonify({"error": "User not found"}), 404


# Health check route
@app.route('/live/compose', methods=['GET'])
def live():
    return jsonify({"status": "Running"})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
