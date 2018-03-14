package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.annotation.UIEventsHandler;
import com.aiexpanse.react.view.api.Event;
import com.aiexpanse.react.view.api.EventsHandler;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerDomainDictionary;
import com.aiexpanse.react.view.factory.api.HandlerFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class UIEventsHandlerAssembler<W extends WidgetContainer> extends AbstractAssembler<UIEventsHandler, W> {

    @Inject
    private HandlerDomainDictionary handlerDomainDictionary;

    @Inject
    private HandlerFactory handlerFactory;

    @Override
    public void populate(UIEventsHandler uiEventsHandler, W widgetContainer) {
        Class<? extends EventsHandler> clazz = uiEventsHandler.clazz();
        HandlerDomain<? extends EventsHandler> handlerDomain = handlerDomainDictionary.getDomain(clazz);
        List<Event> events = handlerFactory.createEvents(handlerDomain);
        widgetContainer.setEvents(events);
    }

}
