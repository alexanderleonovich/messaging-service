package com.leonovich.messagingservice.client;

import com.leonovich.messagingservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Created : 19/06/2021 11:33
 * Project : messaging-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Component
@Slf4j
public class MessagingClient {
    private final WebClient client = WebClient.create("http://localhost:8080");

    @Value("${messaging.service.data.volume}")
    private int volume;

    public Disposable postMessages() {
        return Flux.fromStream(Message.initOfSize(volume))
            .delayElements(Duration.ofMillis(30))
            .doOnComplete(() -> {
                log.info("Execution completed.");
                System.exit(0);
            })
            .subscribe(
                message -> client.post()
                    .uri("/message")
                    .bodyValue(message)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchangeToMono(response -> response.bodyToMono(Message.class))
                    .subscribe(
                        m -> {
                            if (m.isProcessed()) {
                                log.debug("Message:{} logged.", m.getIndex());
                            }
                        },
                        throwable -> log.error("Message:{} processing failed due to {}", message.getIndex(), throwable.getMessage())
                    )
            );
    }
}
