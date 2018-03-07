package com.aiexpanse.dictionary.api;

import java.util.Collection;

public interface DomainDictionary<D extends Domain<?, ?, ?>> {

    D getDomain(Class<?> clazz);

    Collection<D> getAllDomains();

    void addDomain(D domain);

}
