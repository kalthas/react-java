package com.aiexpanse.react.view.factory.api;

import com.aiexpanse.react.view.api.Event;
import com.aiexpanse.react.view.api.EventsHandler;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;

import java.util.List;

public interface HandlerFactory {

    <H extends EventsHandler> List<Event> createEvents(HandlerDomain<H> handlerDomain);

}
