package com.aiexpanse.react.view.factory.checker.impl;

import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.factory.checker.api.CheckResult;
import com.aiexpanse.react.view.factory.checker.api.HandlerCheckRule;
import com.aiexpanse.react.view.factory.checker.api.HandlerChecker;
import com.google.inject.Singleton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class DefaultHandlerChecker implements HandlerChecker {

    private Set<HandlerCheckRule> rules = new HashSet<>();

    public DefaultHandlerChecker() {
        registerRule(new FieldNameCheckRule());
    }

    @Override
    public void check(GuiDomain<?> guiDomain, HandlerDomain<?> handlerDomain) {
        List<CheckResult> errors = rules.stream()
                .map(rule -> rule.apply(guiDomain, handlerDomain))
                .filter(result -> result != null && result.isFailed())
                .collect(Collectors.toList());
        if (!errors.isEmpty()) {
            throw new CheckException("Widget class[" + guiDomain.getDomainClass().getName() + "]", errors);
        }
    }

    @Override
    public void registerRule(HandlerCheckRule rule) {
        rules.add(rule);
    }

}
