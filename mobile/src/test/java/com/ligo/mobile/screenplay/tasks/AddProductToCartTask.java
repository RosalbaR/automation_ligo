package com.ligo.mobile.screenplay.tasks;

import com.ligo.mobile.screenplay.Task;
import com.ligo.mobile.screenplay.actors.Actor;
import com.ligo.mobile.utils.MobileActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddProductToCartTask implements Task {
    private static final By ADD_TO_CART_BUTTON = By.id("com.saucelabs.mydemoapp.android:id/cartBt");

    @Override
    public void performAs(Actor actor) {
        WebDriverWait wait = new WebDriverWait(actor.driver(), Duration.ofSeconds(20));

        scrollToAddToCartButton(actor);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON));
        button.click();
        MobileActionUtils.waitAfterAction();
    }

    private void scrollToAddToCartButton(Actor actor) {
        try {
            actor.driver().findElement(io.appium.java_client.MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/cartBt\").instance(0));"
            ));
        } catch (Exception ignored) {
            actor.driver().findElement(io.appium.java_client.MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Add to cart\").instance(0));"
            ));
        }
    }
}
