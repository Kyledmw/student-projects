package com.appl_maint_mngt.account.controllers.interfaces;

import com.appl_maint_mngt.account.forms.interfaces.ILoginForm;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IAccountController {

    void login(ILoginForm form, IErrorCallback errorCallback);

    void getAuthDetails(String token, IErrorCallback errorCallback);

    void getProfile(Long id, IErrorCallback errorCallback);
}
