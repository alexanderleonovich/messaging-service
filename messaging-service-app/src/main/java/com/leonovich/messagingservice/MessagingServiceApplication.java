package com.leonovich.messagingservice;

import com.leonovich.messagingservice.client.MessagingClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class MessagingServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MessagingServiceApplication.class, args);
        MessagingClient client = context.getBean(MessagingClient.class);
        client.postMessages();
    }

}
