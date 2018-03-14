package com.aiexpanse.react.view.factory.checker.api;

import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;

public interface HandlerCheckRule {

    CheckResult apply(GuiDomain<?> guiDomain, HandlerDomain<?> handlerDomain);

}
