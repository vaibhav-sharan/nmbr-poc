from kafka import KafkaConsumer
from elasticsearch import Elasticsearch
import json
from config import KAFKA_SERVERS, KAFKA_TOPIC, ES_HOST, ES_PORT, INDEX_NAME

consumer = KafkaConsumer(
    KAFKA_TOPIC,
    bootstrap_servers=KAFKA_SERVERS,
    group_id='log_consumer_group',
    value_deserializer=lambda x: json.loads(x.decode('utf-8'))
)

es = Elasticsearch([{'host': ES_HOST, 'port': ES_PORT}])


def create_index_if_not_exists():
    if not es.indices.exists(index=INDEX_NAME):
        es.indices.create(index=INDEX_NAME, body={
            'mappings': {
                'properties': {
                    'timestamp': {'type': 'date'},
                    'level': {'type': 'keyword'},
                    'message': {'type': 'text'},
                    'source': {'type': 'keyword'}
                }
            }
        })


create_index_if_not_exists()

for message in consumer:
    log_entry = message.value
    es.index(index=INDEX_NAME, body=log_entry)
    print(f"Indexed: {log_entry}")
