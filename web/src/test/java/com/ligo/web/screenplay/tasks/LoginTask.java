package com.ligo.web.screenplay.tasks;

import com.ligo.web.pages.LoginPage;
import com.ligo.web.screenplay.Actor;
import com.ligo.web.screenplay.Task;
import com.ligo.web.screenplay.abilities.UseTheWebApp;

public class LoginTask implements Task {
    private final String username;
    private final String password;

    public LoginTask(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void performAs(Actor actor) {
        LoginPage loginPage = new LoginPage(actor.abilityTo(UseTheWebApp.class).getPage());
        loginPage.login(username, password);
    }
}
