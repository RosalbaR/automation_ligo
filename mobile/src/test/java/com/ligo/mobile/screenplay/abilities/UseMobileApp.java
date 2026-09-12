package com.ligo.mobile.screenplay.abilities;

import io.appium.java_client.AppiumDriver;

public class UseMobileApp {
    private final AppiumDriver driver;

    private UseMobileApp(AppiumDriver driver) {
        this.driver = driver;
    }

    public static UseMobileApp withDriver(AppiumDriver driver) {
        return new UseMobileApp(driver);
    }

    public AppiumDriver driver() {
        return driver;
    }
}
