package com.aiexpanse.react.view.dictionary.api;

public interface HandlerDomainParser {

    <F, D extends HandlerDomain<F>> D parseDomain(Class<F> clazz);

}
