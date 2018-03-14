package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.dictionary.impl.AbstractDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerItem;
import com.aiexpanse.react.view.dictionary.api.HandlerRelationship;

public class DefaultHandlerDomain<T> extends AbstractDomain<T, HandlerItem<T, ?>, HandlerRelationship<T>> implements HandlerDomain<T> {
}
