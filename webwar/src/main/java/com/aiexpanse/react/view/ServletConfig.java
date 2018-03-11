package com.aiexpanse.react.view;

import com.aiexpanse.ioc.guice.ViewModule;
import com.aiexpanse.ioc.guice.jackson.JacksonModule;
import com.aiexpanse.react.intf.websockets.guice.InjectConfigurator;
import com.aiexpanse.react.view.dictionary.impl.AnnotationBasedGuiParser;
import com.aiexpanse.react.view.sample.SampleApplication;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class ServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                install(new JacksonModule());
                install(new ViewModule());
                requestStaticInjection(InjectConfigurator.class);
            }
        });
        AnnotationBasedGuiParser parser = injector.getInstance(AnnotationBasedGuiParser.class);
        parser.parseDomain(SampleApplication.class);
        return injector;
    }

}
