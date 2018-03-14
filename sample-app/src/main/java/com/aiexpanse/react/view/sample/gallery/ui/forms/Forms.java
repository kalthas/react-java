package com.aiexpanse.react.view.sample.gallery.ui.forms;

import com.aiexpanse.react.view.annotation.UIView;
import com.aiexpanse.react.view.annotation.UIWidget;
import com.aiexpanse.react.view.impl.DefaultViewGroup;
import com.aiexpanse.react.view.sample.gallery.ui.forms.signin.SignInFormView;

public class Forms extends DefaultViewGroup {

    @UIView
    @UIWidget(displayName = "Sign In Form")
    public SignInFormView signInFormView;

}
