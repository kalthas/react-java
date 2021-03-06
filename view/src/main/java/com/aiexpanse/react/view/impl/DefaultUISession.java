package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.*;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiDomainDictionary;
import com.aiexpanse.react.view.factory.api.WidgetFactory;
import com.aiexpanse.react.view.utils.UIPathUtils;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultUISession implements UISession {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUISession.class);
    private static final Integer ID = ID_GENERATOR.getAndIncrement();

    @Inject
    private UIService uiService;

    @Inject
    private GuiDomainDictionary guiDomainDictionary;

    @Inject
    private WidgetFactory widgetFactory;

    private transient Map<String, Application> applications = new ConcurrentHashMap<>();
    private transient Map<String, Event> events = new ConcurrentHashMap<>();

    @Override
    public Widget getWidget(String uiPath) {
        if (Strings.isNullOrEmpty(uiPath)) {
            LOGGER.warn("cannot get widget because ui path a null or empty");
            throw new RuntimeException("cannot get widget because ui path is null or empty");
        }
        String applicationUiPath = UIPathUtils.getRootUIPath(uiPath);
        if (uiPath.equals(applicationUiPath)) {
            loadApplication(applicationUiPath);
        }
        return uiService.getWidgetByUIPath(uiPath, applications.get(applicationUiPath));
    }

    @Override
    public Widget loadWidget(String uiPath) {
        Widget widget = getWidget(uiPath);
        if (widget instanceof WidgetContainer) {
            WidgetContainer widgetContainer = (WidgetContainer) widget;
            if (!widgetContainer.getContentsLoaded()) {
                widgetFactory.createContents(widgetContainer);
                widgetContainer.getAllContents().forEach(this::registerEvents);
            }
        }
        return widget;
    }

    @Override
    public Event getEventByPath(String eventPath) {
        return events.get(eventPath);
    }

    private void loadApplication(String applicationUiPath) {
        if (!applications.containsKey(applicationUiPath)) {
            synchronized (this) {
                if (!applications.containsKey(applicationUiPath)) {
                    String applicationName = UIPathUtils.getRootName(applicationUiPath);
                    GuiDomain<Application> applicationDomain =
                            (GuiDomain<Application>) guiDomainDictionary.getTopLevelDomain(applicationName);
                    if (applicationDomain == null) {
                        throw new RuntimeException("Cannot find application domain with name: " + applicationName);
                    }
                    Application application = widgetFactory.createWidget(applicationDomain);
                    applications.put(applicationUiPath, application);
                    application.getAllContents().forEach(this::registerEvents);
                }
            }
        }
    }

    private void registerEvents(Widget widget) {
        if (widget instanceof WidgetContainer) {
            WidgetContainer widgetContainer = (WidgetContainer) widget;
            List<Event> events = widgetContainer.getEvents();
            if (events != null) {
                for (Event event : events) {
                    this.events.put(event.getEventPath(), event);
                }
            }
            widgetContainer.getAllContents().forEach(this::registerEvents);
        }
    }

    @Override
    public String key() {
        return ID.toString();
    }

}
