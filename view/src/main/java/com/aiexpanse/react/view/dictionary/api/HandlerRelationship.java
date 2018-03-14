package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.Relationship;

@DDD(name = "UI Event Relationship")
public interface HandlerRelationship<F> extends Relationship<F, Void> {
}