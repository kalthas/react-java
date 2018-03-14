package com.aiexpanse.react.view.sample.gallery.ui.forms.signin;

import com.aiexpanse.react.view.annotation.UIButton;
import com.aiexpanse.react.view.annotation.UIEventsHandler;
import com.aiexpanse.react.view.annotation.UIField;
import com.aiexpanse.react.view.api.Button;
import com.aiexpanse.react.view.api.Field;
import com.aiexpanse.react.view.impl.DefaultForm;

@UIEventsHandler(clazz = SignInFormHandler.class)
public class SignInForm extends DefaultForm {

    @UIField
    public Field<String> account;

    @UIField
    public Field<String> password;

    @UIButton
    public Button submit;

}
