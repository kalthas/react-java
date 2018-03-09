package com.aiexpanse.react.view.factory.checker.api;

public interface WidgetClassCheckResult {

    boolean isFailed();

    String getErrorMessage();
    void setErrorMessage(String errorMessage);

}
