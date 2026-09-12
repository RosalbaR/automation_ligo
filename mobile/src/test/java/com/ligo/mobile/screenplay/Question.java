package com.ligo.mobile.screenplay;

import com.ligo.mobile.screenplay.actors.Actor;

public interface Question<T> {
    T answeredBy(Actor actor);
}
