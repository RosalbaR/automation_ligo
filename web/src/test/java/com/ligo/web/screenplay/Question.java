package com.ligo.web.screenplay;

public interface Question<T> {
    T answeredBy(Actor actor);
}
