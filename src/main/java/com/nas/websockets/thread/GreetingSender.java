package com.nas.websockets.thread;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.nas.websockets.WebSocketConfig;
import com.nas.websockets.entity.Greeting;

@Component
public class GreetingSender {
    
    private final SimpMessagingTemplate SimpMessagingTemplate;

    public GreetingSender(SimpMessagingTemplate SimpMessagingTemplate) {
        this.SimpMessagingTemplate = SimpMessagingTemplate;
    }
    
    public void greet(String greeting) {
        this.SimpMessagingTemplate.convertAndSend(WebSocketConfig.WS_MESSAGE_DESTINATION_GREETING, new Greeting("Hello, this is your message: " + greeting));
    }
}
