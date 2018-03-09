package com.aiexpanse.react.view.factory.checker.impl;

import com.aiexpanse.react.view.factory.checker.api.WidgetClassCheckResult;

import java.util.List;
import java.util.stream.Collectors;

public class WidgetClassCheckException extends RuntimeException {

    private List<WidgetClassCheckResult> errors;

    public WidgetClassCheckException(Class<?> widgetClass, List<WidgetClassCheckResult> errors) {
        super(
                "Widget class[" + widgetClass.getName() + "] failed with "
                + errors.size() + " errors:\n\t"
                + errors.stream().map(WidgetClassCheckResult::getErrorMessage)
                    .collect(Collectors.joining("\n\t"))
        );
        this.errors = errors;
    }

    public List<WidgetClassCheckResult> getErrors() {
        return errors;
    }

}
