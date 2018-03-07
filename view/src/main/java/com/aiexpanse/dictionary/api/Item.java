package com.aiexpanse.dictionary.api;

/**
 * Created by lothair on 05/07/2017.
 */
@DDD(name = "Item")
public interface Item<D, T> extends Member<D> {

    Class<T> getType();

}
