package com.aiexpanse.react.view.factory.checker.impl;

import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiItem;
import com.aiexpanse.react.view.dictionary.api.HandlerDomain;
import com.aiexpanse.react.view.dictionary.api.HandlerItem;
import com.aiexpanse.react.view.factory.checker.api.CheckResult;
import com.aiexpanse.react.view.factory.checker.api.HandlerCheckRule;

import java.util.Map;
import java.util.stream.Collectors;

/*
 * This rule is to check whether field name specified in @UIEvent exists in the widget, e.g.
 *
 *   @UIEventsHandler(clazz=MyFormHandler.class)
 *   class MyForm {
 *       @UIButton
 *       public MyButton button
 *   }
 *
 *   class MyFormHandler {
 *      @UIEvent(field="button", type=ONCLICK)
 *      public void onButtonClick(MyForm form) {
 *      }
 *   }
 *
 * My form indeed has a property named button
 *
 */
public class FieldNameCheckRule implements HandlerCheckRule {

    private static final String PREFIX = "Item[";

    @Override
    public CheckResult apply(GuiDomain<?> guiDomain, HandlerDomain<?> handlerDomain) {
        Map<String, ? extends GuiItem<?, ?>> guiItemMap = guiDomain.getAllItems().stream()
                .collect(Collectors.toMap(GuiItem::getName, item -> item));
        return handlerDomain.getAllItems().stream()
                .filter(handlerItem -> !guiItemMap.containsKey(handlerItem.getName()))
                .findFirst()
                .map(FieldNameCheckRule::createResult)
                .orElse(null);
    }

    private static DefaultCheckResult createResult(HandlerItem handlerItem) {
        return DefaultCheckResult.newInstanceWithError("Item[" + handlerItem.getName() + "] doesn't exist");
    }

}
