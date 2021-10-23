package com.nas.websockets.redis;

import redis.clients.jedis.JedisPubSub;


public class RedisPubSub extends JedisPubSub {
    
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage received. Channel: {}, Msg: {}" + channel + ", " + message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage received. Channel: {}, Msg: {}" + channel + ", " + message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("onSubscribe. Channel: {}, Msg: {}" + channel);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("onUnsubscribe");
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPUnsubscribe");
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe");
    }
}
