package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiDomainDictionary;
import com.google.common.base.Strings;
import com.google.inject.Singleton;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Singleton
public class DefaultGuiDomainDictionary implements GuiDomainDictionary {

    final ConcurrentMap<Class<?>, GuiDomain<?>> domainByClass = new ConcurrentHashMap<>();
    final ConcurrentMap<String, GuiDomain<?>> topLevelDomainByName = new ConcurrentHashMap<>();

    @Override
    public GuiDomain<?> getTopLevelDomain(String domainName) {
        if (Strings.isNullOrEmpty(domainName)) {
            throw new RuntimeException("domainName cannot be null");
        }
        return topLevelDomainByName.get(domainName);
    }

    @Override
    public void addTopLevelDomain(GuiDomain<?> domain) {
        topLevelDomainByName.putIfAbsent(domain.getName(), domain);
    }

    @Override
    public GuiDomain<?> getDomain(Class<?> clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        return domainByClass.get(clazz);
    }

    @Override
    public Collection<GuiDomain<?>> getAllDomains() {
        return null;
    }

    @Override
    public void addDomain(GuiDomain<?> domain) {
        Objects.requireNonNull(domain, "domain is null");
        Objects.requireNonNull(domain.getDomainClass(), "domain class is null");
        domainByClass.putIfAbsent(domain.getDomainClass(), domain);
    }

}
