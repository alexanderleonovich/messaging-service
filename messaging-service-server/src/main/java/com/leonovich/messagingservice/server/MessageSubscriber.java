package com.leonovich.messagingservice.server;

import com.leonovich.messagingservice.model.LogLevel;
import com.leonovich.messagingservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.CoreSubscriber;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created : 18/06/2021 13:12
 * Project : messaging-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Slf4j
@Component
public class MessageSubscriber implements CoreSubscriber<Message> {
    private Subscription subscription;
    public final List<Message> logged = new LinkedList<>();

    @Value("${messaging.service.log.level}")
    private LogLevel logLevel;
    @Value("${messaging.service.request.size}")
    private int requestSize;

    @Override
    public void onSubscribe(Subscription subscription) {
        log.debug("Subscribed.");
        this.subscription = subscription;
        this.subscription.request(requestSize);
    }

    @Override
    public void onNext(Message message) {
        log.debug("PROCESSING:{}", message);
        if (allowToLog().test(message)) {
            message.setProcessed(true);
            logged.add(message);
            log.info("LOGGED:{}", message);
        }
        subscription.request(requestSize);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("System cannot perform action please contact your Administrator.", throwable);
    }

    @Override
    public void onComplete() {
        log.debug("Completed.");
    }

    public List<Message> getLogged() {
        return this.logged;
    }

    private Predicate<Message> allowToLog() {
        return message -> logLevel.priority() < message.getLogLevel().priority();
    }
}
