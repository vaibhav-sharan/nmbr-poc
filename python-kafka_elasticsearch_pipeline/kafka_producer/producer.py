from config import KAFKA_SERVERS, KAFKA_TOPIC
from kafka import KafkaProducer
import json
from datetime import datetime

# Kafka Producer configuration
producer = KafkaProducer(
    bootstrap_servers=KAFKA_SERVERS,
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)


def send_log(level, message, source):
    log_entry = {
        'timestamp': datetime.utcnow().isoformat(),
        'level': level,
        'message': message,
        'source': source
    }
    producer.send(KAFKA_TOPIC, log_entry)
    producer.flush()  # Ensure the message is sent
    print(f"Sent: {log_entry}")


# Example usage
if __name__ == "__main__":
    send_log('INFO', 'This is a test message', 'test_source')
