package com.nas.websockets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.nas.websockets.thread.GreetingThread;

/**
 * Main Spring boot application.
 */
@SpringBootApplication
public class WebsocketsApplication {


    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WebsocketsApplication.class, args);
    }

    /**
     * Task executor.
     *
     * @return not null
     */
    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    /**
     * Greeting thread.
     *
     * @return not null
     */
    @Bean
    public GreetingThread redisThread() {
        return new GreetingThread();
    }

    /**
     * Start the redis background thread within the Spring context.
     *
     * @param taskExecutor not null
     * @return not null
     */
    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor taskExecutor) {
        return args -> taskExecutor.execute(redisThread());
    }
}
