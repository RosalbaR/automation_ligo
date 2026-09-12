package com.ligo.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    public static final By CONFIRMATION_MESSAGE = By.xpath(
            "//*[contains(@text, 'Checkout Complete') or " +
            "contains(@text, 'Thank you for your order') or " +
            "contains(@text, 'Your new swag is on its way') or " +
            "contains(@text, 'Your order has been dispatched and will arrive as fast as the pony gallops!')]");

    public static boolean isVisible(WebDriver driver) {
        return driver.findElements(CONFIRMATION_MESSAGE).size() > 0;
    }
}
