package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.dictionary.api.DomainParser;
import com.aiexpanse.react.view.annotation.UIApplication;
import com.aiexpanse.react.view.api.Application;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetType;
import com.aiexpanse.react.view.dictionary.api.*;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Singleton
public class AnnotationBasedGuiParser implements DomainParser {

    @Inject
    private GuiDomainDictionary domainDictionary;

    @Inject
    private GuiDomainParserHelper parserHelper;

    @Override
    public <F, D extends GuiDomain<F>> D parseDomain(Class<F> clazz) {
        return (D)parse(clazz);
    }

    private GuiDomain<?> parse(Class<?> widgetClass) {
        GuiDomain<?> domain = domainDictionary.getDomain(widgetClass);
        if (domain != null) {
            return domain;
        }
        if (WidgetType.isLeafType(widgetClass)) {
            return null;
        }
        domain = parseDomainClass(widgetClass);
        parseRelationshipAndItems(widgetClass, (DefaultGuiDomain)domain);
        domainDictionary.addDomain(domain);

        if (domain.getWidgetType().equals(WidgetType.APPLICATION)) {
            if (!Strings.isNullOrEmpty(domain.getName())) {
                domainDictionary.addTopLevelDomain(domain);
            }
        }

        return domain;
    }

    private void parseRelationshipAndItems(Class<?> widgetClass, DefaultGuiDomain domain) {
        Field[] fields = widgetClass.getFields();
        for (int i = 0; i< fields.length; i++) {
            Field field = fields[i];
            Annotation[] annotations = field.getAnnotations();
            if (annotations == null || annotations.length == 0) {
                continue;
            }
            if (!Widget.class.isAssignableFrom(field.getType())) {
                continue;
            }
            if (WidgetType.isLeafType(field.getType())) {
                parseItem(domain, field, i);
            } else {
                parseRelationship(domain, field, i);
            }
        }
    }

    private <T> void parseItem(DefaultGuiDomain<T> domain, Field field, int i) {
        GuiItem item = domain.getItem(field.getName());
        if (item == null) {
            item = parserHelper.createItem(field);
            item.setDomain(domain);
            ((DefaultGuiItem)item).setType(field.getType());
            item.setName(field.getName());
            item.setDeclaredField(field);
            item.setIndex(i);
            domain.addItem(item);
        }
    }

    private <T> void parseRelationship(DefaultGuiDomain<T> domain, Field field, int i) {
        GuiRelationship relationship = domain.getRelationship(field.getName());
        if (relationship == null) {
            GuiDomain<?> endingDomain = parse(field.getType());
            relationship = parserHelper.createRelationship(field);
            relationship.setName(field.getName());
            relationship.setDomain(domain);
            relationship.setEndingDomain(endingDomain);
            relationship.setDeclaredField(field);
            relationship.setIndex(i);
            domain.addRelationship(relationship);
        }
    }

    private GuiDomain<?> parseDomainClass(Class clazz) {
        DefaultGuiDomain<?> domain = parserHelper.createDomain(clazz);
        domain.setDomainClass(clazz);
        if (Application.class.isAssignableFrom(clazz)) {
            UIApplication uiApplication = (UIApplication)clazz.getAnnotation(UIApplication.class);
            if (uiApplication != null) {
                domain.setName(uiApplication.name());
            } else {
                domain.setName(clazz.getSimpleName());
            }
        } else {
            domain.setName(clazz.getSimpleName());
        }
        domain.setWidgetType(WidgetType.getWidgetTypeByClass(clazz));
        return domain;
    }

}
