package com.nas.websockets.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.nas.websockets.WebSocketConfig;
import com.nas.websockets.entity.Greeting;
import com.nas.websockets.entity.HelloMessage;

/**
 * Allows web clients to send messages to be posted to a web sockets channel for other clients to receive.
 */
@Controller
public class GreetingController {

    /**
     * Handles messages from the client.
     * @param message from client
     * @return greeting
     */
    @MessageMapping("/hello")
    @SendTo(WebSocketConfig.WS_MESSAGE_DESTINATION_GREETING)
    public Greeting greeting(HelloMessage message) {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}