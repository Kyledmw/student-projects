package com.appl_maint_mngt.account.views.interfaces;

import android.view.View;
import android.widget.Button;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface ILoginView {

    String getEmailInput();
    String getPasswordInput();
    void setLoginOnClickListener(View.OnClickListener listener);
    void disableLoginButton();
    void enableLoginButton();
    void resetView();
}
