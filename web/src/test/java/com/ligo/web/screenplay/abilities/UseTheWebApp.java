package com.ligo.web.screenplay.abilities;

import com.ligo.web.screenplay.Ability;
import com.microsoft.playwright.Page;

public class UseTheWebApp implements Ability {
    private final Page page;

    public UseTheWebApp(Page page) {
        this.page = page;
    }

    public static UseTheWebApp on(Page page) {
        return new UseTheWebApp(page);
    }

    public Page getPage() {
        return page;
    }
}
