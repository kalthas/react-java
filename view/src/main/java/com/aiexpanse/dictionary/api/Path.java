package com.aiexpanse.dictionary.api;

import java.util.List;

/**
 * Created by lothair on 19/08/2017.
 */
public interface Path<F, T, I extends Item<?, T>, R extends Relationship<?, ?>> {

    Domain<F, ?, ?> getStartingDomain();

    boolean isTerminatedWithAnItem();

    R getEndingRelationship();

    I getTerminatingItem();

    List<R> getRelationships();

}
