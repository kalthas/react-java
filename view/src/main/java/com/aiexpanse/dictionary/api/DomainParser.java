package com.aiexpanse.dictionary.api;

import com.aiexpanse.react.view.dictionary.api.GuiDomain;

public interface DomainParser {

    <F, D extends GuiDomain<F>> D parseDomain(Class<F> clazz);

}
