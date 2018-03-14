package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.Item;

import java.lang.reflect.Method;

@DDD(name = "UI Event Item")
public interface HandlerItem<F, T> extends Item<F, T> {

    @Override
    HandlerDomain<F> getDomain();
    void setDomain(HandlerDomain<F> domain);

    Method getDeclaredMethod();
    void setDeclaredMethod(Method method);

}
