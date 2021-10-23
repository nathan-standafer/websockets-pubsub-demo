package com.nas.websockets.thread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;


public class GreetingThread implements Runnable{

    @Autowired
    private GreetingSender targetcontroller;
    

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(5000);
            }
            catch (Exception e)
            {}
            publishGreeting();
        }
    }
    
    private void publishGreeting()
    {
        targetcontroller.greet("The date is " + new Date());
    }
    
}