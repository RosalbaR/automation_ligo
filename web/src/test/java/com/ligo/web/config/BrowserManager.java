package com.ligo.web.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserManager {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;

    public static Page startBrowser() {
        if (page == null) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            page = browser.newPage();
            page.setViewportSize(1440, 900);
        }
        return page;
    }

    public static Page getPage() {
        if (page == null) {
            return startBrowser();
        }
        return page;
    }

    public static void pause(int milliseconds) {
        if (page != null) {
            page.waitForTimeout(milliseconds);
        }
    }

    public static void close() {
        if (page != null) {
            page.close();
            page = null;
        }
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
