package com.appl_maint_mngt.account.services.interfaces;

import com.appl_maint_mngt.account.forms.interfaces.ILoginForm;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IUserAuthService {

    void postLogin(ILoginForm form, IErrorCallback errorCallback);

    void getDetails(String token, IErrorCallback errorCallback);
}
