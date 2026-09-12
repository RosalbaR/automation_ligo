package com.ligo.web.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public boolean containsProduct(String productName) {
        Locator item = page.locator(".inventory_item_name")
                .filter(new Locator.FilterOptions().setHasText(productName));
        return item.isVisible();
    }

    public void checkout() {
        page.click("#checkout");
    }
}
