package com.ligo.mobile.pages;

import org.openqa.selenium.By;

public class ProductCatalogPage {
    public static final By CART_BUTTON = By.id("com.saucelabs.mydemoapp.android:id/cartRL");
    public static final By PRODUCT_IMAGE = By.xpath("//*[@resource-id='com.saucelabs.mydemoapp.android:id/productIV' or @content-desc='Product Image']");

    public static By product(String productName) {
        String safeProduct = productName == null ? "Sauce Labs Backpack" : productName.trim();
        String escaped = safeProduct.replace("'", "\\'");
        return By.xpath(
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and (@text='" + escaped + "' or @content-desc='" + escaped + "' or contains(@text, '" + escaped + "'))] | " +
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/productIV' and contains(@content-desc, '" + escaped + "')] | " +
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and contains(@text, '" + escaped + "')] | " +
                "//*[@text='" + escaped + "' or @content-desc='" + escaped + "' or contains(@text, '" + escaped + "')]"
        );
    }

    public static By productContainer(String productName) {
        String safeProduct = productName == null ? "Sauce Labs Backpack" : productName.trim();
        String escaped = safeProduct.replace("'", "\\'");
        return By.xpath(
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and (@text='" + escaped + "' or @content-desc='" + escaped + "' or contains(@text, '" + escaped + "'))]/ancestor::android.view.ViewGroup[1] | " +
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/productIV' and contains(@content-desc, '" + escaped + "')]/ancestor::android.view.ViewGroup[1] | " +
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and contains(@text, '" + escaped + "')]/ancestor::android.view.ViewGroup[1]"
        );
    }

    public static By productImage(String productName) {
        String safeProduct = productName == null ? "Sauce Labs Backpack" : productName.trim();
        String escaped = safeProduct.replace("'", "\\'");
        return By.xpath(
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/productIV' and (contains(@content-desc, '" + escaped + "') or contains(@content-desc, 'Product Image'))] | " +
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and (@text='" + escaped + "' or contains(@text, '" + escaped + "'))]/preceding-sibling::*[@resource-id='com.saucelabs.mydemoapp.android:id/productIV'][1]"
        );
    }
}
