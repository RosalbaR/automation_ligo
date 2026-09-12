package com.ligo.mobile.pages;

import org.openqa.selenium.By;

public class CartPage {
    public static final By PROCEED_TO_CHECKOUT = By.xpath(
            "//*[@resource-id='com.saucelabs.mydemoapp.android:id/checkoutBtn' or " +
            "@resource-id='com.saucelabs.mydemoapp.android:id/proceedToCheckoutBtn' or " +
            "@resource-id='com.saucelabs.mydemoapp.android:id/checkoutTV' or " +
            "@resource-id='com.saucelabs.mydemoapp.android:id/checkoutBtn' or " +
            "@resource-id='com.saucelabs.mydemoapp.android:id/checkoutTV' or " +
            "contains(@resource-id,'checkout') or contains(@resource-id,'proceed') or " +
            "contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CHECKOUT') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CHECKOUT') or " +
            "contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'PROCEED TO CHECKOUT') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'PROCEED TO CHECKOUT') or " +
            "contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CONTINUE') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CONTINUE')]");
}
