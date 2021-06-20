package com.leonovich.messagingservice.model;

import java.util.Random;

/**
 * Created : 18/06/2021 13:31
 * Project : messaging-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
public class Enums {

    private Enums() {
    }

    public static <T extends Enum<T>> T random(Class<T> enumClass) {
        return random(enumClass.getEnumConstants());
    }

    public static <T> T random(T ... values) {
        return values[new Random().nextInt(values.length)];
    }
}
