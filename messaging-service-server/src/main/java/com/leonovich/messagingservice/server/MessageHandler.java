package com.leonovich.messagingservice.server;

import com.leonovich.messagingservice.model.Message;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created : 19/06/2021 11:29
 * Project : messaging-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Component
public class MessageHandler {

    private final Subscriber<Message> subscriber;

    @Autowired
    public MessageHandler(Subscriber<Message> subscriber) {
        this.subscriber = subscriber;
    }

    public Mono<ServerResponse> handle(ServerRequest request) {
        return request.bodyToMono(Message.class)
            .flatMap(msg -> {
                Mono<Message> messageMono = Mono.just(msg);
                messageMono.subscribe(subscriber);
                return messageMono;
            })
            .flatMap(msg ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(msg)));
    }
}
