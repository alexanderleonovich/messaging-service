package com.leonovich.messagingservice.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * Created : 18/06/2021 12:54
 * Project : messaging-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Message implements Serializable, Automated {
    private static final AtomicLong INCREMENT = new AtomicLong();

    private Long index;
    private String content;
    private LocalDateTime timestamp;
    private LogLevel logLevel;
    private boolean processed;

    @Override
    public <T> T populate() {
        this.setIndex(INCREMENT.incrementAndGet());
        this.setContent(RandomString.DEFAULT.get());
        this.setLogLevel(Enums.random(LogLevel.class));
        this.setTimestamp(LocalDateTime.now());
        return (T) this;
    }

    public static Message init() {
        return new Message().populate();
    }

    public static Stream<Message> initOfSize(int size) {
        return Stream.generate(Message::init).limit(size);
    }
}
