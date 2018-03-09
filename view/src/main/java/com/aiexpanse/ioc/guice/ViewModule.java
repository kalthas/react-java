package com.aiexpanse.ioc.guice;

import com.aiexpanse.react.view.TypeConfig;
import com.aiexpanse.react.view.dictionary.api.GuiDomainDictionary;
import com.aiexpanse.react.view.dictionary.api.GuiDomainParser;
import com.aiexpanse.react.view.dictionary.api.GuiDomainParserHelper;
import com.aiexpanse.react.view.dictionary.impl.AnnotationBasedGuiParser;
import com.aiexpanse.react.view.dictionary.impl.DefaultGuiDomainDictionary;
import com.aiexpanse.react.view.dictionary.impl.DefaultGuiDomainParserHelper;
import com.aiexpanse.react.view.factory.api.WidgetFactory;
import com.aiexpanse.react.view.factory.checker.api.WidgetClassChecker;
import com.aiexpanse.react.view.factory.checker.impl.DefaultWidgetClassChecker;
import com.aiexpanse.react.view.factory.impl.DefaultWidgetFactory;
import com.google.inject.AbstractModule;

public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GuiDomainDictionary.class).to(DefaultGuiDomainDictionary.class);
        bind(GuiDomainParserHelper.class).to(DefaultGuiDomainParserHelper.class);
        bind(WidgetClassChecker.class).to(DefaultWidgetClassChecker.class);
        bind(GuiDomainParser.class).to(AnnotationBasedGuiParser.class);
        bind(WidgetFactory.class).to(DefaultWidgetFactory.class);
        requestStaticInjection(TypeConfig.class);
    }

}
