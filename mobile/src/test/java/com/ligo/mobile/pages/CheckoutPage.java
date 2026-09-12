package com.ligo.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    public static final By CHECKOUT_TITLE = By.xpath(
            "//*[contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CHECKOUT') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CHECKOUT')]");

    public static final By PLACE_ORDER_BUTTON = By.xpath(
            "//*[contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'PLACE ORDER') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'PLACE ORDER') or " +
            "contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'FINISH') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'FINISH') or " +
            "contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CONFIRM') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CONFIRM')]");

    public static final By TO_PAYMENT_BUTTON = By.xpath(
            "//*[contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'TO PAYMENT') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'TO PAYMENT')]"
    );

    public static final By REVIEW_ORDER_BUTTON = By.xpath(
            "//*[contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'REVIEW ORDER') or " +
            "contains(translate(@content-desc,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'REVIEW ORDER')]");

    public static final By ADDRESS_INPUTS = By.className("android.widget.EditText");

    public static void completePurchase(WebDriver driver) {
        completeCheckout(driver,
                "Rebecca Winter",
                "Mandorley 112",
                "Entrance 1",
                "Truro",
                "Cornwall",
                "89750",
                "United Kingdom");
        completePayment(driver,
                "Rebecca Winter",
                "3258 1256 7568 7891",
                "03/25",
                "123");
    }

    public static void completeCheckout(WebDriver driver, String fullName, String addressLine1,
                                       String addressLine2, String city, String state, String zipCode,
                                       String country) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> inputs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ADDRESS_INPUTS, 0));
        if (inputs.size() >= 7) {
            fillField(inputs.get(0), fullName);
            fillField(inputs.get(1), addressLine1);
            fillField(inputs.get(2), addressLine2);
            fillField(inputs.get(3), city);
            fillField(inputs.get(4), state);
            fillField(inputs.get(5), zipCode);
            fillField(inputs.get(6), country);
        }

        WebElement paymentButton = wait.until(ExpectedConditions.elementToBeClickable(TO_PAYMENT_BUTTON));
        paymentButton.click();
    }

    public static void completePayment(WebDriver driver, String cardHolderName, String cardNumber,
                                      String expirationDate, String securityCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> inputs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ADDRESS_INPUTS, 0));
        if (inputs.size() >= 4) {
            fillField(inputs.get(0), cardHolderName);
            fillField(inputs.get(1), cardNumber);
            fillField(inputs.get(2), expirationDate);
            fillField(inputs.get(3), securityCode);
        }

        WebElement reviewOrder = wait.until(ExpectedConditions.elementToBeClickable(REVIEW_ORDER_BUTTON));
        reviewOrder.click();

        WebElement placeOrder = wait.until(ExpectedConditions.elementToBeClickable(PLACE_ORDER_BUTTON));
        placeOrder.click();
    }

    public static void completePurchase(WebDriver driver, String fullName, String addressLine1,
                                       String addressLine2, String city, String state, String zipCode,
                                       String country, String cardHolderName, String cardNumber,
                                       String expirationDate, String securityCode) {
        completeCheckout(driver, fullName, addressLine1, addressLine2, city, state, zipCode, country);
        completePayment(driver, cardHolderName, cardNumber, expirationDate, securityCode);
    }

    private static void fillField(WebElement field, String value) {
        if (field == null) {
            return;
        }
        field.clear();
        field.sendKeys(" ");
        field.clear();
        if (value != null && !value.isBlank()) {
            field.sendKeys(value);
        }
    }
}
