import redis

class RedisOperator():

    def __init__(self, redisClient=None):
        if redisClient is None:
            redisClient = redis.Redis(
                host='localhost',
                port=6379, 
                password='')
        self.redisClient = redisClient

    def get(self, key):
        return self.redisClient.get(key)

    def set(self, key, value):        
        self.redisClient.set(key, value)

    def delete(self, key):
        return self.redisClient.delete(key)    