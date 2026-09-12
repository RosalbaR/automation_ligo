package com.ligo.mobile.screenplay.actors;

import com.ligo.mobile.screenplay.Question;
import com.ligo.mobile.screenplay.Task;
import com.ligo.mobile.screenplay.abilities.UseMobileApp;
import io.appium.java_client.AppiumDriver;
import java.util.HashMap;
import java.util.Map;

public class Actor {
    private final String name;
    private final Map<Class<?>, Object> abilities = new HashMap<>();

    private Actor(String name) {
        this.name = name;
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    public Actor can(UseMobileApp ability) {
        abilities.put(UseMobileApp.class, ability);
        return this;
    }

    public AppiumDriver driver() {
        return usingApp().driver();
    }

    public UseMobileApp usingApp() {
        if (!abilities.containsKey(UseMobileApp.class)) {
            throw new IllegalStateException("Actor " + name + " has no mobile app ability configured.");
        }
        return (UseMobileApp) abilities.get(UseMobileApp.class);
    }

    public void attemptsTo(Task... tasks) {
        for (Task task : tasks) {
            task.performAs(this);
        }
    }

    public <T> T asksFor(Question<T> question) {
        return question.answeredBy(this);
    }
}
