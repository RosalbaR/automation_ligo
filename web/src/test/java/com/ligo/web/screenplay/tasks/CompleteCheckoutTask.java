package com.ligo.web.screenplay.tasks;

import com.ligo.web.pages.CartPage;
import com.ligo.web.pages.CheckoutPage;
import com.ligo.web.screenplay.Actor;
import com.ligo.web.screenplay.Task;
import com.ligo.web.screenplay.abilities.UseTheWebApp;

public class CompleteCheckoutTask implements Task {
    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public CompleteCheckoutTask(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    @Override
    public void performAs(Actor actor) {
        CartPage cartPage = new CartPage(actor.abilityTo(UseTheWebApp.class).getPage());
        cartPage.checkout();

        CheckoutPage checkoutPage = new CheckoutPage(actor.abilityTo(UseTheWebApp.class).getPage());
        checkoutPage.fillCustomerInformation(firstName, lastName, postalCode);
        checkoutPage.finishOrder();
    }
}
