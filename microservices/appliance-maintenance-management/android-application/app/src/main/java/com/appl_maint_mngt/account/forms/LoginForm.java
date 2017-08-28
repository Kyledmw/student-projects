package com.appl_maint_mngt.account.forms;

import com.appl_maint_mngt.account.forms.interfaces.ILoginForm;

/**
 * Created by Kyle on 07/04/2017.
 */

public class LoginForm implements ILoginForm {

    private String email;
    private String password;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
