package com.aiexpanse.react.view.factory.checker.impl;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.factory.checker.api.WidgetClassCheckResult;
import com.aiexpanse.react.view.factory.checker.api.WidgetClassCheckRule;
import com.aiexpanse.react.view.factory.checker.api.WidgetClassChecker;
import com.google.inject.Singleton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class DefaultWidgetClassChecker implements WidgetClassChecker {

    private Set<WidgetClassCheckRule> rules = new HashSet<>();

    public DefaultWidgetClassChecker() {
        registerRule(new FieldAnnotationCheckRule());
    }

    @Override
    public void check(Class<? extends Widget> widgetClass) {
        List<WidgetClassCheckResult> errors = rules.stream()
                .map(rule -> rule.apply(widgetClass))
                .filter(result -> result != null && result.isFailed())
                .collect(Collectors.toList());
        if (!errors.isEmpty()) {
            throw new WidgetClassCheckException(widgetClass, errors);
        }
    }

    @Override
    public void registerRule(WidgetClassCheckRule rule) {
        rules.add(rule);
    }

}
