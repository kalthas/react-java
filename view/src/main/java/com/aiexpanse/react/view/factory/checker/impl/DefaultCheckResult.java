package com.aiexpanse.react.view.factory.checker.impl;

import com.aiexpanse.react.view.factory.checker.api.CheckResult;
import com.google.common.base.Strings;

public class DefaultCheckResult implements CheckResult {

    private boolean failed = false;
    private String errorMessage;

    public static DefaultCheckResult newInstanceWithError(String errorMessage) {
        DefaultCheckResult result = new DefaultCheckResult();
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
