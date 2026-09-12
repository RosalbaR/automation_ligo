package com.ligo.mobile.screenplay.questions;

import com.ligo.mobile.pages.CartPage;
import com.ligo.mobile.pages.CheckoutPage;
import com.ligo.mobile.screenplay.Question;
import com.ligo.mobile.screenplay.actors.Actor;

public class CheckoutIsVisibleQuestion implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        return actor.driver().findElements(CartPage.PROCEED_TO_CHECKOUT).size() > 0
                || actor.driver().findElements(CheckoutPage.CHECKOUT_TITLE).size() > 0;
    }
}
