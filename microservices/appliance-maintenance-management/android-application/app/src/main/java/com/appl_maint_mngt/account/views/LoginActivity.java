package com.appl_maint_mngt.account.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.controllers.interfaces.IAccountController;
import com.appl_maint_mngt.account.events.IUserAuthEvents;
import com.appl_maint_mngt.account.events.IUserProfileEvents;
import com.appl_maint_mngt.account.forms.LoginForm;
import com.appl_maint_mngt.account.forms.interfaces.ILoginForm;
import com.appl_maint_mngt.account.repositories.constants.IAccountObserverUpdateTypes;
import com.appl_maint_mngt.account.repositories.interfaces.IAccountReadableRepository;
import com.appl_maint_mngt.account.validation.LoginFormValidator;
import com.appl_maint_mngt.account.validation.interfaces.ILoginFormValidator;
import com.appl_maint_mngt.account.views.interfaces.IDashboardForUserTypeRetriever;
import com.appl_maint_mngt.account.views.interfaces.ILoginView;
import com.appl_maint_mngt.account.views.utility.DashboardForUserTypeRetriever;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.errors.interfaces.IErrorPayload;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.validation.interfaces.IValidatorResponse;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.common.views.dialogs.error.ErrorAlertDialogBuilder;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.Arrays;
import java.util.Observable;

public class LoginActivity extends ACommonActivity {
    private static final Logger logger = LoggerManager.getLogger(LoginActivity.class);

    private IAccountController accountController;

    private ILoginView loginView;
    private ILoginFormValidator loginFormValidator;

    private IDashboardForUserTypeRetriever dashboardForUserTypeRetriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logger.i("Creating LoginActivity.");
        IntegrationController.getInstance().getRepositoryController().clear();

        loginView = new LoginView(this);
        loginFormValidator = new LoginFormValidator();
        dashboardForUserTypeRetriever = new DashboardForUserTypeRetriever();

        accountController = IntegrationController.getInstance().getControllerFactory().createAccountController();

        loginView.setLoginOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.i("Entered Login onClick");
                loginView.disableLoginButton();
                ILoginForm form = new LoginForm();
                form.setEmail(loginView.getEmailInput());
                form.setPassword(loginView.getPasswordInput());

                IValidatorResponse validatorResponse = loginFormValidator.validate(form);
                if(validatorResponse.isValid()) {
                    logger.i("LoginForm valid, sending login request to controller.");
                    accountController.login(form, new LoginErrorCallback());
                } else {
                    logger.i("LoginForm invalid, displaying errors.");
                    new ErrorAlertDialogBuilder().build(LoginActivity.this, validatorResponse.getErrors()).show();
                    loginView.enableLoginButton();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        logger.i("Entered onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logger.i("Entered onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logger.i("Entered onPause()");
    }

    @Override
    public void updateModels() {

    }

    @Override
    protected void startObserving() {
        ApplicationEventBus.getInstance().addObserver(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeAccountRepository(this);
    }

    @Override
    protected void stopObserving() {
        ApplicationEventBus.getInstance().deleteObserver(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveAccounntRepository(this);
    }

    @Override
    protected void updateView() {
        loginView.resetView();
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.i("Received Update from Observable.");
        IAccountReadableRepository repository = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository();
        if(arg.equals(IUserAuthEvents.LOGIN_EVENT)) {
            logger.i("Entered Update: %s", IUserAuthEvents.LOGIN_EVENT);
        } else if(arg.equals(IUserAuthEvents.AUTH_EVENT)) {
            logger.i("Entered Update: %s", IUserAuthEvents.AUTH_EVENT);
            accountController.getProfile(repository.get().getId(), new LoginErrorCallback());
        } else if(arg.equals(IUserProfileEvents.GET_EVENT)) {
            logger.i("Entered Update: %s", IUserProfileEvents.GET_EVENT);
            Intent dashboardIntent = new Intent(this, dashboardForUserTypeRetriever.get(repository.get().getUserType()));
            startActivity(dashboardIntent);
        } else if(arg.equals(IAccountObserverUpdateTypes.TOKEN_UPDATE)) {
            logger.i("Entered Update: %s", IAccountObserverUpdateTypes.TOKEN_UPDATE);
            accountController.getAuthDetails(repository.get().getToken(), new LoginErrorCallback());
        }
    }

    private class LoginErrorCallback implements IErrorCallback {

        @Override
        public void callback(IErrorPayload payload) {
            logger.i("Entered AuthDetailsErrorCallback.callback(): %s", Arrays.toString(payload.getErrors().toArray()));
            new ErrorAlertDialogBuilder().build(LoginActivity.this, payload.getErrors()).show();
            loginView.resetView();
            IntegrationController.getInstance().getRepositoryController().clear();
        }
    }
    @Override
    public void onBackPressed() {
    }
}
