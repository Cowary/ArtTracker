package com.ruderu.arttracker.util;

public class Wait {

    public static void seconds(int amount) {
        millis(amount * 1000L);
    }

    public static void millis(long amount) {
        try {
            Thread.sleep(amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
