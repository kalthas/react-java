package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.Domain;

@DDD(name = "UI Event Domain")
public interface HandlerDomain<T> extends Domain<T, HandlerItem<T, ?>, HandlerRelationship<T>> {
}
