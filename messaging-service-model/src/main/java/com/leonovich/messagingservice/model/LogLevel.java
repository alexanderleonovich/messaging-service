package com.leonovich.messagingservice.model;

/**
 * Created : 18/06/2021 12:54
 * Project : messaging-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
public enum LogLevel {
    DEBUG(100), INFO(200), WARNING(300), ERROR(400);

    private final int priority;

    LogLevel(int priority) {
        this.priority = priority;
    }

    public int priority() {
        return priority;
    }
}
