package com.leonovich.messagingservice.client;

import com.leonovich.messagingservice.server.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created : 19/06/2021 11:30
 * Project : messaging-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Configuration
@ComponentScan
public class MessageRouter {

    @Bean
    public RouterFunction<ServerResponse> route(MessageHandler handler) {
        return RouterFunctions.route()
            .POST("/message", handler::handle).build();
    }
}
