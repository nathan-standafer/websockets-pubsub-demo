package com.nas.websockets.redis;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.nas.websockets.WebSocketConfig;
import com.nas.websockets.entity.Greeting;

import redis.clients.jedis.JedisPubSub;

@Component  
public class RedisPubSubBean extends JedisPubSub {

    private final SimpMessagingTemplate simpMessagingTemplate;
    
    public RedisPubSubBean(SimpMessagingTemplate SimpMessagingTemplate) {
        this.simpMessagingTemplate = SimpMessagingTemplate;
    }
    
    
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage received. Channel: {}, Msg: {}" + channel + ", " + message);
        this.simpMessagingTemplate.convertAndSend(WebSocketConfig.WS_MESSAGE_DESTINATION_GREETING, new Greeting("Hello, this is your message: " + message));
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

