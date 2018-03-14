package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerDomainDictionary;
import com.google.inject.Singleton;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Singleton
public class DefaultHandlerDomainDictionary implements HandlerDomainDictionary {

    final ConcurrentMap<Class<?>, HandlerDomain<?>> domainByClass = new ConcurrentHashMap<>();

    @Override
    public HandlerDomain<?> getDomain(Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        return domainByClass.get(clazz);
    }

    @Override
    public Collection<HandlerDomain<?>> getAllDomains() {
        return null;
    }

    @Override
    public void addDomain(HandlerDomain<?> domain) {
        Objects.requireNonNull(domain, "domain is null");
        Objects.requireNonNull(domain.getDomainClass(), "domain class is null");
        domainByClass.putIfAbsent(domain.getDomainClass(), domain);
    }

}
