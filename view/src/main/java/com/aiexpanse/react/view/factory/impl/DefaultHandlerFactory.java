package com.aiexpanse.react.view.factory.impl;

import com.aiexpanse.react.view.api.Event;
import com.aiexpanse.react.view.api.EventsHandler;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerItem;
import com.aiexpanse.react.view.factory.api.HandlerFactory;
import com.aiexpanse.react.view.impl.DefaultEvent;
import com.aiexpanse.react.view.internal.EventHandler;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DefaultHandlerFactory implements HandlerFactory {

    @Inject
    private Injector injector;

    @Override
    public <H extends EventsHandler> List<Event> createEvents(HandlerDomain<H> handlerDomain) {
        H handler = injector.getInstance(handlerDomain.getDomainClass());
        return handlerDomain.getAllItems().stream()
                .map(item -> createEvent(item, handler))
                .collect(Collectors.toList());
    }

    private Event createEvent(HandlerItem handlerItem, EventsHandler eventsHandler) {
        EventHandler eventHandler = new EventHandler(handlerItem, eventsHandler);
        return new DefaultEvent(eventHandler);
    }

}
