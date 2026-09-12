package com.ligo.mobile.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class AppiumDriverFactory {

    public static AndroidDriver createDriver() {
        try {
            String appiumUrl = System.getProperty("appium.server.url", "http://127.0.0.1:4723/wd/hub");
            String deviceName = System.getProperty("device.name", "Xiaomi Redmi Note 14");
            String platformVersion = System.getProperty("platform.version", "16");
            String udid = System.getProperty("udid", "m7fenzm7hemnmzof");
            String apkRelativePath = System.getProperty("mobile.apk.path", "");
            File apkFile = apkRelativePath == null || apkRelativePath.isBlank()
                    ? null
                    : Paths.get(System.getProperty("user.dir"), apkRelativePath).toFile();

            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");
            options.setDeviceName(deviceName);
            options.setPlatformVersion(platformVersion);
            options.setCapability("noReset", true);
            options.setCapability("newCommandTimeout", 300);
            options.setCapability("autoGrantPermissions", true);

            if (udid != null && !udid.isEmpty()) {
                options.setUdid(udid);
            }

            String appPackage = System.getProperty("app.package", "com.saucelabs.mydemoapp.android");
            String appActivity = System.getProperty("app.activity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");

            // Fallback: some devices launch MainActivity instead of SplashActivity. Wait for either activity (or use wildcard) to avoid session start failures.
            String mainActivity = "com.saucelabs.mydemoapp.android.view.activities.MainActivity";

            if (appPackage != null && !appPackage.isBlank() && appActivity != null && !appActivity.isBlank()) {
                options.setAppPackage(appPackage);
                options.setAppActivity(appActivity);
                options.setCapability("appWaitPackage", appPackage);
                // Wait for the configured activity, the actual MainActivity, or any activity as a last resort.
                String appWaitActivity = appActivity + "," + mainActivity + ",*";
                options.setCapability("appWaitActivity", appWaitActivity);
                return new AndroidDriver(new URL(appiumUrl), options);
            }

            if (apkFile != null && apkFile.exists()) {
                options.setApp(apkFile.getAbsolutePath());
                return new AndroidDriver(new URL(appiumUrl), options);
            }

            throw new IllegalStateException("No app was configured for Appium. Set app.package + app.activity or mobile.apk.path.");
        } catch (Exception e) {
            throw new RuntimeException("Unable to initialize Appium driver for the mobile Android app.", e);
        }
    }
}
