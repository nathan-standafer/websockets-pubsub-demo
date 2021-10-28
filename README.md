# websockets-pubsub-demo
A Spring Boot web application that allows clients to see pub/sub messages that are posted to a redis server.

## Project Description
Redis has the capability to allow a service to publish messages as multiple clients subscribe and listen for those messages.  In this project, a message can be published from the redis-cli.  Web clients will display the messages as they are published.  Multiple application servers can each publish the messages to multiple clients in real time.

## Running the Application
Requirements:
- Java 11+ must be installed on the machine.
- A redis server must be running on localhost on the default port (6379)

Start the Spring Boot web application with the following command:
```
linux:    ./mvnw spring-boot:run
windows:  mvnw spring-boot:run
```

Open a web browser and navigate to: http://localhost:8080.  Click the "Connect" button.

Open the redis-cli from a terminal window with the "redis-cli" command.  Publish some messages to the "greeging_channel" channel.
![alt text](https://github.com/nathan-standafer/websockets-pubsub-demo/blob/main/images/redis-cli.png?raw=true)

View the messages on the browser.
![alt text](https://github.com/nathan-standafer/websockets-pubsub-demo/blob/main/images/web-client.png?raw=true)



