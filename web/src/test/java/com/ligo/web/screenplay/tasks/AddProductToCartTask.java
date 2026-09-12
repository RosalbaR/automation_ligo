package com.ligo.web.screenplay.tasks;

import com.ligo.web.pages.ProductsPage;
import com.ligo.web.screenplay.Actor;
import com.ligo.web.screenplay.Task;
import com.ligo.web.screenplay.abilities.UseTheWebApp;

public class AddProductToCartTask implements Task {
    private final String productName;

    public AddProductToCartTask(String productName) {
        this.productName = productName;
    }

    @Override
    public void performAs(Actor actor) {
        ProductsPage productsPage = new ProductsPage(actor.abilityTo(UseTheWebApp.class).getPage());
        productsPage.addToCart(productName);
    }
}
