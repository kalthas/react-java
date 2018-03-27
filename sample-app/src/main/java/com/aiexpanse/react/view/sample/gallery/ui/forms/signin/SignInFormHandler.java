package com.aiexpanse.react.view.sample.gallery.ui.forms.signin;

import com.aiexpanse.react.view.annotation.UIEvent;
import com.aiexpanse.react.view.api.EventType;
import com.aiexpanse.react.view.impl.AbstractEventsHandler;

public class SignInFormHandler extends AbstractEventsHandler {

    @UIEvent(field = "submit", type = EventType.ONCLICK)
    public void onSubmit(SignInForm form) {
        System.out.println("form.submit.ONCLICK");
        System.out.println("\t- account: " + form.account.getValue());
        System.out.println("\t- password: " + form.password.getValue());
    }

}
