package com.nas.websockets.thread;

import org.springframework.beans.factory.annotation.Autowired;

import com.nas.websockets.redis.RedisPubSubBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class GreetingThread implements Runnable {
    
    @Autowired
    private RedisPubSubBean redisPubSubBean;
    
    public final static String CHANNEL_NAME = "greeting_channel";
    


    /**
     * Connect to a redis server and subscribe to a channel. 
     */
    @Override
    public void run() {
        
        while(true) {
            JedisPool jedisPool = null;
            try {
                JedisPoolConfig poolConfig = new JedisPoolConfig();
                jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 0);
                Jedis subscriberJedis = jedisPool.getResource();
                
                System.out.println("Subscribing to \"" + CHANNEL_NAME + "\". This thread will be blocked.");
                subscriberJedis.subscribe(redisPubSubBean, CHANNEL_NAME);  //this will block forever.  I assume until an error of some kind occurs.
                
                redisPubSubBean.unsubscribe();
                System.out.println("Subscription to \"\" + CHANNEL_NAME + \"\" ended.");
                
                jedisPool.returnResource(subscriberJedis);
                jedisPool.close();
                System.out.println("subscription resource complete.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    Thread.sleep(10000);  //sleep a little before attempting to re-connect }
                    jedisPool.close();
                } catch (Exception e) {}
            }
        }
    }
    

    
}