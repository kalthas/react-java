package com.aiexpanse.dictionary.api;

/**
 * Created by lothair on 05/07/2017.
 */
public interface Parser<T> {
    T parse(Class<?> clazz);
}
