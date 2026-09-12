package com.ligo.web.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductsPage {
    private final Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

    public boolean isProductVisible(String productName) {
        Locator product = page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName));
        return product.isVisible();
    }

    public void addToCart(String productName) {
        Locator product = page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName));
        product.locator("button").click();
    }

    public void goToCart() {
        page.click(".shopping_cart_link");
    }
}
