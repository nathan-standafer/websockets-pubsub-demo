package com.nas.websockets.thread;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.nas.websockets.WebSocketConfig;
import com.nas.websockets.entity.Greeting;

@Component
public class GreetingMessagingSender {
    
    private final SimpMessagingTemplate simpMessagingTemplate;

    public GreetingMessagingSender(SimpMessagingTemplate SimpMessagingTemplate) {
        this.simpMessagingTemplate = SimpMessagingTemplate;
    }
    
    public void greet(String greeting) {
        this.simpMessagingTemplate.convertAndSend(WebSocketConfig.WS_MESSAGE_DESTINATION_GREETING, new Greeting("Hello, this is your message: " + greeting));
    }
}
