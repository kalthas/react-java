package com.aiexpanse.react.view.factory.checker.impl;

import com.aiexpanse.react.view.factory.checker.api.CheckResult;

import java.util.List;
import java.util.stream.Collectors;

public class CheckException extends RuntimeException {

    private List<CheckResult> errors;

    public CheckException(String checkOperation, List<CheckResult> errors) {
        super(
                checkOperation + " failed with "
                + errors.size() + " errors:\n\t"
                + errors.stream().map(CheckResult::getErrorMessage)
                    .collect(Collectors.joining("\n\t"))
        );
        this.errors = errors;
    }

    public List<CheckResult> getErrors() {
        return errors;
    }

}
