package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.annotation.UIEvent;
import com.aiexpanse.react.view.api.EventsHandler;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerDomainDictionary;
import com.aiexpanse.react.view.dictionary.api.HandlerDomainParser;
import com.aiexpanse.react.view.dictionary.api.HandlerItem;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.lang.reflect.Method;

@Singleton
public class AnnotationBasedHandlerParser implements HandlerDomainParser {

    @Inject
    private HandlerDomainDictionary domainDictionary;

    @Inject
    private Injector injector;

    @Override
    public <F, D extends HandlerDomain<F>> D parseDomain(Class<F> clazz) {
        if (!EventsHandler.class.isAssignableFrom(clazz)) {
            throw new RuntimeException("Class[" + clazz.getName() + "] is not a handler class");
        }
        return (D)parse((Class<? extends EventsHandler>)clazz);
    }

    private HandlerDomain<?> parse(Class<? extends EventsHandler> handlerClass) {
        HandlerDomain<? extends EventsHandler> domain = domainDictionary.getDomain(handlerClass);
        if (domain != null) {
            return domain;
        }
        domain = parseDomainClass(handlerClass);
        parseItems(handlerClass, (DefaultHandlerDomain)domain);
        domainDictionary.addDomain(domain);

        return domain;
    }

    private void parseItems(Class<?> handlerClass, DefaultHandlerDomain domain) {
        Method[] methods = handlerClass.getMethods();
        for (Method method : methods) {
            UIEvent annotation = method.getAnnotation(UIEvent.class);
            if (annotation != null) {
                HandlerItem handlerItem = parseItem(domain, method);
                handlerItem.setName(annotation.field());
                handlerItem.setEventType(annotation.type());
            }
        }
    }

    private <T> HandlerItem parseItem(DefaultHandlerDomain<T> domain, Method method) {
        HandlerItem item = domain.getItem(method.getName());
        if (item == null) {
            item = injector.getInstance(DefaultHandlerItem.class);
            item.setDomain(domain);
            item.setName(method.getName());
            item.setDeclaredMethod(method);
            domain.addItem(item);
        }
        return item;
    }

    private HandlerDomain<? extends EventsHandler> parseDomainClass(Class<? extends EventsHandler> clazz) {
        DefaultHandlerDomain<? extends EventsHandler> domain = injector.getInstance(DefaultHandlerDomain.class);
        domain.setDomainClass((Class)clazz);
        return domain;
    }

}
