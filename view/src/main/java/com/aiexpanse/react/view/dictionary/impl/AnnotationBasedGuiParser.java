package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.dictionary.api.DomainParser;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.google.inject.Singleton;

@Singleton
public class AnnotationBasedGuiParser implements DomainParser {

    @Override
    public <F, D extends GuiDomain<F>> D parseDomain(Class<F> clazz) {
        return null;
    }

}
