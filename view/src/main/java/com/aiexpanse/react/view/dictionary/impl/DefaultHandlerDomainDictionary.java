package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.api.EventsHandler;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerDomainDictionary;
import com.google.inject.Singleton;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Singleton
public class DefaultHandlerDomainDictionary implements HandlerDomainDictionary {

    private final ConcurrentMap<Class<?>, HandlerDomain<? extends EventsHandler>> domainByClass = new ConcurrentHashMap<>();

    @Override
    public HandlerDomain<? extends EventsHandler> getDomain(Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        return domainByClass.get(clazz);
    }

    @Override
    public Collection<HandlerDomain<? extends EventsHandler>> getAllDomains() {
        return Collections.unmodifiableCollection(domainByClass.values());
    }

    @Override
    public void addDomain(HandlerDomain<? extends EventsHandler> domain) {
        Objects.requireNonNull(domain, "domain is null");
        Objects.requireNonNull(domain.getDomainClass(), "domain class is null");
        domainByClass.putIfAbsent(domain.getDomainClass(), domain);
    }

}
