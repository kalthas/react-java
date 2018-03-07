package com.aiexpanse.dictionary.api;

/**
 * Created by lothair on 05/07/2017.
 */
@DDD(name = "Relationship")
public interface Relationship<F, T> extends Member<F> {

    Domain<T, ?, ?> getEndingDomain();

}
