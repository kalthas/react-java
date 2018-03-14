package com.aiexpanse.react.view.factory.checker.api;

public interface CheckResult {

    boolean isFailed();

    String getErrorMessage();
    void setErrorMessage(String errorMessage);

}
