package com.aiexpanse.dictionary.impl;

import com.aiexpanse.dictionary.api.Domain;
import com.aiexpanse.dictionary.api.Item;
import com.aiexpanse.dictionary.api.Relationship;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractDomain<D, I extends Item<D, ?>, R extends Relationship<D, ?>> implements Domain<D, I, R> {

    protected String name;
    protected Class<D> domainClass;
    private final Map<String, I> itemsByName = new LinkedHashMap<>();
    private final Map<String, R> relationshipsByName = new LinkedHashMap<>();

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Class<D> getDomainClass() {
        return domainClass;
    }

    public void setDomainClass(Class<D> domainClass) {
        this.domainClass = domainClass;
    }

    @Override
    public Collection<I> getAllItems() {
        return Collections.unmodifiableCollection(itemsByName.values());
    }

    @Override
    public I getItem(String name) {
        return itemsByName.get(name);
    }

    public void addItem(I item) {
        itemsByName.put(item.getName(), item);
    }

    @Override
    public Collection<R> getAllRelationships() {
        return Collections.unmodifiableCollection(relationshipsByName.values());
    }

    @Override
    public R getRelationship(String name) {
        return relationshipsByName.get(name);
    }

    public void addRelationship(R relationship) {
        relationshipsByName.put(relationship.getName(), relationship);
    }

}