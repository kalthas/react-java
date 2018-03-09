package com.aiexpanse.react.view.factory.impl;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiDomainParser;
import com.aiexpanse.react.view.dictionary.api.GuiMember;
import com.aiexpanse.react.view.dictionary.api.GuiRelationship;
import com.aiexpanse.react.view.factory.api.WidgetFactory;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

@Singleton
public class DefaultWidgetFactory implements WidgetFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWidgetFactory.class);

    @Inject
    private GuiDomainParser domainParser;

    @Inject
    private Injector injector;

    @Override
    public <W extends Widget> W createWidget(Class<W> widgetClass) {
        GuiDomain<W> domain = domainParser.parseDomain(widgetClass);
        if (domain == null) {
            throw new RuntimeException("Cannot find domain for widget class: " + widgetClass);
        }
        return createWidget(domain);
    }

    @Override
    public <W extends Widget> W createWidget(GuiDomain<W> guiDomain) {
        return createWidget(guiDomain, null);
    }

    private <W extends Widget> W createWidget(GuiDomain<W> guiDomain, GuiRelationship<?, W> relationship) {
        W widget = injector.getInstance(guiDomain.getDomainClass());
        populatePropertiesFromDomain(guiDomain, widget);
        if (relationship != null) {
            relationship.getUIAnnotations().forEach(uiAnnotation -> {
                uiAnnotation.populate(widget);
            });
        }
        if (widget instanceof WidgetContainer) {
            WidgetContainer widgetContainer = (WidgetContainer) widget;
            populateChildrenFromItems(guiDomain, widgetContainer);
            if (widgetContainer.getEager()) {
                populateChildrenFromRelationships(guiDomain, widgetContainer);
            }
        }
        return widget;
    }

    public <W extends Widget> W createWidget(GuiRelationship<?, W> relationship) {
        GuiDomain<W> endingDomain = relationship.getEndingDomain();
        W widget = createWidget(endingDomain, relationship);
        // TBD: reorder-index and populate event
        return widget;
    }

    private <W extends Widget> void populatePropertiesFromDomain(GuiDomain<W> guiDomain, W widget) {
        widget.setName(guiDomain.getName());
        widget.setDomain(guiDomain);
        guiDomain.getUIAnnotations().forEach(uiAnnotation -> {
            uiAnnotation.populate(widget);
        });
    }

    private <W extends Widget, C extends WidgetContainer> void populateChildrenFromItems(GuiDomain<W> guiDomain, C widgetContainer) {
        guiDomain.getAllItems().forEach(item -> {
            Widget childWidget = (Widget)injector.getInstance(item.getType());
            childWidget.setIndex(item.getIndex());
            populatePropertiesFromMember(item, childWidget, widgetContainer);
        });
    }

    private <W extends Widget, C extends WidgetContainer> void populateChildrenFromRelationships(GuiDomain<W> guiDomain, C widgetContainer) {
        for (GuiRelationship relationship : guiDomain.getAllRelationships()) {
            Widget childWidget = createWidget(relationship);
            childWidget.setIndex(relationship.getIndex());
            populatePropertiesFromMember(relationship, childWidget, widgetContainer);
        }
        widgetContainer.setContentsLoaded(true);
    }

    private <M extends GuiMember<?, ?>, C extends WidgetContainer> void populatePropertiesFromMember(M member, Widget childWidget, C widgetContainer) {
        addWidgetToParent(member, childWidget, widgetContainer);
        member.getUIAnnotations().forEach(uiAnnotation -> {
            uiAnnotation.populate(childWidget);
        });
    }

    private <M extends GuiMember, C extends WidgetContainer> void addWidgetToParent(M member, Widget childWidget, C widgetContainer) {
        childWidget.setName(member.getName());
        widgetContainer.add(childWidget);
        stitchChildWidgetToDeclaredField(member.getDeclaredField(), widgetContainer, childWidget);
    }

    private <C extends WidgetContainer> void stitchChildWidgetToDeclaredField(Field declaredField, C widgetContainer, Widget childWidget) {
        try {
            declaredField.set(widgetContainer, childWidget);
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to stitch widget child [{}] to field [{}]", childWidget.getName(), declaredField.toString(), e);
        }
    }

}
