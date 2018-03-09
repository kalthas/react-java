package com.aiexpanse.react.view.dictionary.api;

public interface GuiDomainParser {

    <F, D extends GuiDomain<F>> D parseDomain(Class<F> clazz);

}
