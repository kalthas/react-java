package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.api.EventType;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerItem;

import java.lang.reflect.Method;

public class DefaultHandlerItem<F, T> implements HandlerItem<F, T> {

    protected String name;
    private HandlerDomain<F> definingDomain;
    private Method declaredMethod;
    private EventType eventType;

    @Override
    public HandlerDomain<F> getDomain() {
        return definingDomain;
    }

    @Override
    public void setDomain(HandlerDomain<F> domain) {
        this.definingDomain = domain;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public Method getDeclaredMethod() {
        return declaredMethod;
    }

    @Override
    public void setDeclaredMethod(Method method) {
        this.declaredMethod = method;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Class<T> getType() {
        return null;
    }

}
