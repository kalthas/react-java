package com.aiexpanse.ioc.guice;

import com.aiexpanse.react.view.dictionary.api.GuiDomainDictionary;
import com.aiexpanse.react.view.dictionary.api.GuiDomainParserHelper;
import com.aiexpanse.react.view.dictionary.impl.DefaultGuiDomainDictionary;
import com.aiexpanse.react.view.dictionary.impl.DefaultGuiDomainParserHelper;
import com.google.inject.AbstractModule;

public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GuiDomainDictionary.class).to(DefaultGuiDomainDictionary.class);
        bind(GuiDomainParserHelper.class).to(DefaultGuiDomainParserHelper.class);
    }

}
