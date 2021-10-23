package com.nas.websockets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.nas.websockets.thread.GreetingThread;

@SpringBootApplication
public class WebsocketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketsApplication.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor(); // Or use another one of your liking
    }
    
    @Bean
    public GreetingThread redisThread() {
        return new GreetingThread();
    }
    
    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor taskExecutor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                taskExecutor.execute(redisThread());
            }
        };
    }
}
