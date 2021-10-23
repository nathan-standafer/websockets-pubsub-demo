package com.nas.websockets.thread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.nas.websockets.redis.RedisPubSubBean;
import com.nas.websockets.redis.RedisSubcriber;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class GreetingThread implements Runnable{

    @Autowired
    private GreetingSender greetingSender;
    
    @Autowired
    private RedisPubSubBean redisPubSubBean;
    
    private final JedisPoolConfig poolConfig = new JedisPoolConfig();
    private final JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 0);
    private final Jedis subscriberJedis = jedisPool.getResource();
    //private final RedisPubSub redisPubSub = new RedisPubSub();

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(5000);
            }
            catch (Exception e)
            {}
            publishGreeting();
            subscribe();
        }
    }
    
    private void publishGreeting()
    {
        greetingSender.greet("The date is " + new Date());
    }
    
    private void subscribe()
    {
        System.out.println("Subscribing to \"commonChannel\". This thread will be blocked.");
        subscriberJedis.subscribe(redisPubSubBean, RedisSubcriber.CHANNEL_NAME);
        System.out.println("Subscription ended.");
        redisPubSubBean.unsubscribe();
        jedisPool.returnResource(subscriberJedis);
        System.out.println("subscription resource complete.");
    }
    
}