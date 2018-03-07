package com.aiexpanse.dictionary.api;

/**
 * Created by lothair on 05/07/2017.
 */
@DDD(name = "Member")
public interface Member<D> {

    @DDI(name = "Name")
    String getName();
    void setName(String name);

    Domain<D, ?, ?> getDomain();

}
