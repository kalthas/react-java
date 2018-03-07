package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.DomainDictionary;

public interface GuiDomainDictionary extends DomainDictionary<GuiDomain<?>> {

    GuiDomain<?> getTopLevelDomain(String domainName);

    void addTopLevelDomain(GuiDomain<?> domain);

}
