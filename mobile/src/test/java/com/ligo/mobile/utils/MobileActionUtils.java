package com.ligo.mobile.utils;

public final class MobileActionUtils {
    public static final long ACTION_DELAY_MS = 5000L;

    private MobileActionUtils() {
    }

    public static void waitAfterAction() {
        try {
            Thread.sleep(ACTION_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
