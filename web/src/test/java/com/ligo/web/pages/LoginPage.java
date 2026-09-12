package com.ligo.web.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        page.fill("#user-name", username);
        page.fill("#password", password);
        page.click("#login-button");
    }

    public void loginAsStandardUser() {
        login("standard_user", "secret_sauce");
    }

    public boolean isErrorVisible() {
        return page.locator("[data-test='error']").isVisible();
    }

    public String getErrorMessage() {
        return page.locator("[data-test='error']").textContent();
    }
}
