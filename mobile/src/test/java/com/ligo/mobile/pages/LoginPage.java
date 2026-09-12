package com.ligo.mobile.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final Duration TIMEOUT = Duration.ofSeconds(15);

    public static final By MENU_BUTTON = By.id("com.saucelabs.mydemoapp.android:id/menuIV");
    public static final By LOGIN_MENU_OPTION = By.xpath(
            "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Recycler view for menu\"]/android.view.ViewGroup[11]"
    );
    public static final By USERNAME_INPUT = By.id("com.saucelabs.mydemoapp.android:id/nameET");
    public static final By PASSWORD_INPUT = By.id("com.saucelabs.mydemoapp.android:id/passwordET");
    public static final By LOGIN_BUTTON = By.id("com.saucelabs.mydemoapp.android:id/loginBtn");

    public static void login(AppiumDriver driver, String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        wait.until(ExpectedConditions.elementToBeClickable(MENU_BUTTON)).click();
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_MENU_OPTION)).click();

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        usernameField.sendKeys(username);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
    }
}
