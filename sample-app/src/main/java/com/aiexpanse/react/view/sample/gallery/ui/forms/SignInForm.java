package com.aiexpanse.react.view.sample.gallery.ui.forms;

import com.aiexpanse.react.view.annotation.UIButton;
import com.aiexpanse.react.view.annotation.UIField;
import com.aiexpanse.react.view.api.Button;
import com.aiexpanse.react.view.api.Field;
import com.aiexpanse.react.view.impl.DefaultForm;

public class SignInForm extends DefaultForm {

    @UIField
    public Field<String> account;

    @UIField
    public Field<String> password;

    @UIButton
    public Button submit;

}
