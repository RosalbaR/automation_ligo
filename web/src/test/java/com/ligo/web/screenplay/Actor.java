package com.ligo.web.screenplay;

import java.util.HashMap;
import java.util.Map;

public class Actor {
    private final String name;
    private final Map<Class<? extends Ability>, Ability> abilities = new HashMap<>();

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public <A extends Ability> void can(A ability) {
        abilities.put(ability.getClass(), ability);
    }

    public <A extends Ability> A abilityTo(Class<A> abilityType) {
        return abilityType.cast(abilities.get(abilityType));
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
