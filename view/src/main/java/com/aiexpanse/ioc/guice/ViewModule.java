package com.aiexpanse.ioc.guice;

import com.aiexpanse.react.view.api.*;
import com.aiexpanse.react.view.dictionary.api.*;
import com.aiexpanse.react.view.dictionary.impl.*;
import com.aiexpanse.react.view.factory.api.HandlerFactory;
import com.aiexpanse.react.view.factory.api.WidgetFactory;
import com.aiexpanse.react.view.factory.checker.api.HandlerChecker;
import com.aiexpanse.react.view.factory.checker.api.WidgetClassChecker;
import com.aiexpanse.react.view.factory.checker.impl.DefaultHandlerChecker;
import com.aiexpanse.react.view.factory.checker.impl.DefaultWidgetClassChecker;
import com.aiexpanse.react.view.factory.impl.DefaultHandlerFactory;
import com.aiexpanse.react.view.factory.impl.DefaultWidgetFactory;
import com.aiexpanse.react.view.impl.*;
import com.google.inject.AbstractModule;

public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GuiDomainDictionary.class).to(DefaultGuiDomainDictionary.class);
        bind(GuiDomainParserHelper.class).to(DefaultGuiDomainParserHelper.class);
        bind(WidgetClassChecker.class).to(DefaultWidgetClassChecker.class);
        bind(GuiDomainParser.class).to(AnnotationBasedGuiParser.class);
        bind(WidgetFactory.class).to(DefaultWidgetFactory.class);
        bind(UIService.class).to(DefaultUIService.class);
        bind(UISession.class).to(DefaultUISession.class);

        bind(Application.class).to(DefaultApplication.class);
        bind(Perspective.class).to(DefaultPerspective.class);
        bind(ViewGroup.class).to(DefaultViewGroup.class);
        bind(View.class).to(DefaultView.class);
        bind(Form.class).to(DefaultForm.class);
        bind(Button.class).to(DefaultButton.class);
        bind(Field.class).to(DefaultField.class);

        bind(HandlerDomainDictionary.class).to(DefaultHandlerDomainDictionary.class);
        bind(HandlerDomainParser.class).to(AnnotationBasedHandlerParser.class);
        bind(HandlerChecker.class).to(DefaultHandlerChecker.class);
        bind(HandlerFactory.class).to(DefaultHandlerFactory.class);

        bind(HandlerDomain.class).to(DefaultHandlerDomain.class);
        bind(HandlerItem.class).to(DefaultHandlerItem.class);
    }

}
