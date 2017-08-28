package com.appl_maint_mngt.account.views;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.views.interfaces.ILoginView;

/**
 * Created by Kyle on 07/04/2017.
 */

public class LoginView implements ILoginView {

    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;


    public LoginView(Activity parent) {
        emailField = (EditText) parent.findViewById(R.id.login_edittext_email);
        passwordField = (EditText) parent.findViewById(R.id.login_edittext_password);
        loginButton = (Button) parent.findViewById(R.id.login_button_submit);
    }

    @Override
    public String getEmailInput() {
        return emailField.getEditableText().toString();
    }

    @Override
    public String getPasswordInput() {
        return passwordField.getEditableText().toString();
    }

    @Override
    public void setLoginOnClickListener(View.OnClickListener listener) {
        loginButton.setOnClickListener(listener);
    }

    @Override
    public void disableLoginButton() {
        loginButton.setEnabled(false);
    }

    @Override
    public void enableLoginButton() {
        loginButton.setEnabled(true);
    }

    @Override
    public void resetView() {
        passwordField.getEditableText().clear();
        enableLoginButton();
    }
}
