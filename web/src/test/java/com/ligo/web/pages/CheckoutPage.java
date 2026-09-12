package com.ligo.web.pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void fillCustomerInformation(String firstName, String lastName, String postalCode) {
        page.fill("#first-name", firstName);
        page.fill("#last-name", lastName);
        page.fill("#postal-code", postalCode);
        page.click("#continue");
    }

    public String getOrderTotal() {
        return page.locator(".summary_total_label").textContent();
    }

    public void finishOrder() {
        page.click("#finish");
    }

    public String getConfirmationHeader() {
        return page.locator(".complete-header").textContent();
    }
}
