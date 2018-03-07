package com.aiexpanse.dictionary.api;

import java.util.Collection;

/**
 * Created by lothair on 05/07/2017.
 */
@DDD(name = "Domain")
public interface Domain<D, I extends Item<D, ?>, R extends Relationship<D, ?>> {

    @DDI(name = "Name")
    String getName();

    @DDI(name = "Domain Class")
    Class<D> getDomainClass();

    Collection<I> getAllItems();

    Collection<R> getAllRelationships();

    I getItem(String name);

    R getRelationship(String name);

}
