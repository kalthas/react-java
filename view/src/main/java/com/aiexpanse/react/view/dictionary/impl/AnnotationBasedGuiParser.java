package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.TypeConfig;
import com.aiexpanse.react.view.factory.api.UIAnnotation;
import com.aiexpanse.react.view.annotation.UIApplication;
import com.aiexpanse.react.view.api.Application;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetType;
import com.aiexpanse.react.view.dictionary.api.*;
import com.aiexpanse.react.view.factory.checker.api.WidgetClassChecker;
import com.aiexpanse.utils.TypeUtils;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Singleton
public class AnnotationBasedGuiParser implements GuiDomainParser {

    @Inject
    private GuiDomainDictionary domainDictionary;

    @Inject
    private GuiDomainParserHelper parserHelper;

    @Inject
    private WidgetClassChecker widgetClassChecker;

    @Inject
    private TypeConfig typeConfig;

    @Override
    public <F, D extends GuiDomain<F>> D parseDomain(Class<F> clazz) {
        if (!Widget.class.isAssignableFrom(clazz)) {
            throw new RuntimeException("Class[" + clazz.getName() + "] is not widget class");
        }
        return (D)parse((Class<? extends Widget>)clazz);
    }

    private GuiDomain<?> parse(Class<? extends Widget> widgetClass) {
        GuiDomain<?> domain = domainDictionary.getDomain(widgetClass);
        if (domain != null) {
            return domain;
        }
        if (WidgetType.isLeafType(widgetClass)) {
            return null;
        }
        widgetClassChecker.check(widgetClass);
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
        Set<Class<? extends Widget>> acceptableContentTypes = typeConfig.getAcceptableContentTypes(domain.getWidgetType());
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
            if (TypeUtils.anySubType(acceptableContentTypes, field.getType()) == null) {
                throw new RuntimeException("Widget[" + field.getType() + "] is not acceptable as child of ["
                        + domain.getDomainClass().getName() + " as type " + domain.getWidgetType() + "]");
            }
            List<UIAnnotation> uiAnnotations = new ArrayList<>();
            for (Annotation annotation : annotations) {
                UIAnnotation uiAnnotation = TypeConfig.getUIAnnotation(annotation);
                if (uiAnnotation != null) {
                    uiAnnotations.add(uiAnnotation);
                }
            }
            GuiMember member;
            if (WidgetType.isLeafType(field.getType())) {
                member = parseItem(domain, field, i);
            } else {
                member = parseRelationship(domain, field, i);
            }
            member.setUIAnnotations(uiAnnotations);
        }
    }

    private <T> GuiMember parseItem(DefaultGuiDomain<T> domain, Field field, int i) {
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
        return item;
    }

    private <T> GuiMember parseRelationship(DefaultGuiDomain<T> domain, Field field, int i) {
        GuiRelationship relationship = domain.getRelationship(field.getName());
        if (relationship == null) {
            GuiDomain<?> endingDomain = parseDomain(field.getType());
            relationship = parserHelper.createRelationship(field);
            relationship.setName(field.getName());
            relationship.setDomain(domain);
            relationship.setEndingDomain(endingDomain);
            relationship.setDeclaredField(field);
            relationship.setIndex(i);
            domain.addRelationship(relationship);
        }
        return relationship;
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
        List<UIAnnotation> uiAnnotations = new ArrayList<>();
        for (Annotation annotation : clazz.getAnnotations()) {
            UIAnnotation uiAnnotation = TypeConfig.getUIAnnotation(annotation);
            if (uiAnnotation != null) {
                uiAnnotations.add(uiAnnotation);
            }
        }
        domain.setUIAnnotations(uiAnnotations);
        return domain;
    }

}
