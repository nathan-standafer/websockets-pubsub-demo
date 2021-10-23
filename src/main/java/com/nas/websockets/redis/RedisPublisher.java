package com.nas.websockets.redis;

import redis.clients.jedis.Jedis;

public class RedisPublisher {


    private final Jedis publisherJedis;

    private final String channel;

    public RedisPublisher(Jedis publisherJedis, String channel) {
        this.publisherJedis = publisherJedis;
        this.channel = channel;
    }

    public void publishSomething(String something) {
        
        publisherJedis.publish(channel, something);

    }
}