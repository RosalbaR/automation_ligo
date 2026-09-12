package com.ligo.web.screenplay.questions;

import com.ligo.web.pages.CheckoutPage;
import com.ligo.web.screenplay.Actor;
import com.ligo.web.screenplay.Question;
import com.ligo.web.screenplay.abilities.UseTheWebApp;

public class OrderConfirmationQuestion implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        CheckoutPage checkoutPage = new CheckoutPage(actor.abilityTo(UseTheWebApp.class).getPage());
        return checkoutPage.getConfirmationHeader();
    }
}
