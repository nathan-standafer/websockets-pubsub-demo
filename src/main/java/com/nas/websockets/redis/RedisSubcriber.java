package com.nas.websockets.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisSubcriber {

    public static final String CHANNEL_NAME = "commonChannel";
    
    private final JedisPoolConfig poolConfig = new JedisPoolConfig();
    private final JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 0);
    private final Jedis subscriberJedis = jedisPool.getResource();
    private final RedisPubSub redisPubSub = new RedisPubSub();
    
    
    public void subscribe() {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Subscribing to \"commonChannel\". This thread will be blocked.");
                    subscriberJedis.subscribe(redisPubSub, CHANNEL_NAME);
                    System.out.println("Subscription ended.");
                    
                    redisPubSub.unsubscribe();
                    jedisPool.returnResource(subscriberJedis);
                } catch (Exception e) {
                    System.out.println("Subscribing failed." +  e.getMessage());
                }
            }
        }).start();

    }
    
    public void publishSomething() {
        Jedis publisherJedis = jedisPool.getResource();

        RedisPublisher redisPublisher = new RedisPublisher(publisherJedis, CHANNEL_NAME);
        redisPublisher.publishSomething("I published something!!!!");
        System.out.println("Message Published.");
    }
    
    
    public static void main(String[] args) throws Exception
    {
        RedisSubcriber redisSubcriber = new RedisSubcriber();
        redisSubcriber.subscribe();   
        
        Thread.sleep(2000);
        System.out.println("XXXXX");
        
        redisSubcriber.publishSomething();
    }
    

}
