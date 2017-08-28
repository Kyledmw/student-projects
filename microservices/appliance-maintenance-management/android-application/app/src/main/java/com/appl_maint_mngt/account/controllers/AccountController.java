package com.appl_maint_mngt.account.controllers;

import com.appl_maint_mngt.account.controllers.interfaces.IAccountController;
import com.appl_maint_mngt.account.forms.interfaces.ILoginForm;
import com.appl_maint_mngt.account.services.interfaces.IUserAuthService;
import com.appl_maint_mngt.account.services.interfaces.IUserProfileService;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;


/**
 * Created by Kyle on 07/04/2017.
 */
public class AccountController implements IAccountController {
    private static final Logger logger = LoggerManager.getLogger(AccountController.class);

    private IUserAuthService userAuthService;
    private IUserProfileService userProfileService;

    public AccountController() {
        logger.i("Constructing AccountController");
        userAuthService = IntegrationController.getInstance().getServiceFactory().createUserAuthService();
        userProfileService = IntegrationController.getInstance().getServiceFactory().createUserProfileService();
    }

    @Override
    public void login(ILoginForm form, IErrorCallback errorCallback) {
        logger.i("Performing login for: %s", form.getEmail());
        userAuthService.postLogin(form, errorCallback);
    }

    @Override
    public void getAuthDetails(String token, IErrorCallback errorCallback) {
        logger.i("Getting auth details for token: %s", token);
        userAuthService.getDetails(token, errorCallback);
    }

    @Override
    public void getProfile(Long id, IErrorCallback errorCallback) {
        logger.i("Getting profile for id: %d", id);
        userProfileService.get(id, errorCallback);
    }
}
