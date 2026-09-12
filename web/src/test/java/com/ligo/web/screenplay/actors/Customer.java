package com.ligo.web.screenplay.actors;

import com.ligo.web.screenplay.Actor;

public class Customer {
    public static Actor named(String name) {
        return new Actor(name);
    }
}
