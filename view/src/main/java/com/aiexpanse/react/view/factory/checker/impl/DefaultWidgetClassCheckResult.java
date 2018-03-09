package com.aiexpanse.react.view.factory.checker.impl;

import com.aiexpanse.react.view.factory.checker.api.WidgetClassCheckResult;
import com.google.common.base.Strings;

public class DefaultWidgetClassCheckResult implements WidgetClassCheckResult {

    private boolean failed = false;
    private String errorMessage;

    public static DefaultWidgetClassCheckResult newInstanceWithError(String errorMessage) {
        DefaultWidgetClassCheckResult result = new DefaultWidgetClassCheckResult();
        result.setErrorMessage(errorMessage);
        return result;
    }

    @Override
    public boolean isFailed() {
        return failed;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        if (!Strings.isNullOrEmpty(errorMessage)) {
            this.failed = true;
            this.errorMessage = errorMessage;
        }
    }

}
