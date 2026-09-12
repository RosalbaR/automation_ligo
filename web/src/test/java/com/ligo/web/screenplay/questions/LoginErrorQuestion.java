package com.ligo.web.screenplay.questions;

import com.ligo.web.pages.LoginPage;
import com.ligo.web.screenplay.Actor;
import com.ligo.web.screenplay.Question;
import com.ligo.web.screenplay.abilities.UseTheWebApp;

public class LoginErrorQuestion implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        LoginPage loginPage = new LoginPage(actor.abilityTo(UseTheWebApp.class).getPage());
        return loginPage.getErrorMessage();
    }
}
