package com.aiexpanse.react.view.factory.assembler.api;

public interface Assembler<T, W> {

    void populate(T t, W w);

}
