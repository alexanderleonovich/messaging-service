package com.leonovich.messagingservice.model;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Created : 18/06/2021 13:34
 * Project : messaging-service
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
public enum RandomString {
    DEFAULT(() -> generate(new Random().nextInt(8 - 2) + 2));

    private final Supplier<String> supplier;
    private static final Random r = new Random();
    private static final char[] CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] LOWERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] VOWELS = "aeiou".toCharArray();

    RandomString(Supplier<String> supplier) {
        this.supplier = supplier;
    }

    public String get() {
        return supplier.get();
    }

    private static String generate(int length) {
        StringBuilder sb = new StringBuilder();
        sb.append(CAPITALS[r.nextInt(CAPITALS.length)]);
        for (int i = 0; i < length; i++) {
            sb.append(VOWELS[r.nextInt(VOWELS.length)]);
            sb.append(LOWERS[r.nextInt(LOWERS.length)]);
        }
        return sb.toString();
    }
}
