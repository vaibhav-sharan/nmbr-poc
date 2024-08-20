from elasticsearch import Elasticsearch
from ..kafka_consumer.config import ES_HOST, ES_PORT, INDEX_NAME

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
