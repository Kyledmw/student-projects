package com.appl_maint_mngt.account.services.dummy;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.events.IUserAuthEvents;
import com.appl_maint_mngt.account.forms.LoginForm;
import com.appl_maint_mngt.account.forms.interfaces.ILoginForm;
import com.appl_maint_mngt.account.models.AAccount;
import com.appl_maint_mngt.account.models.Account;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.web.AuthDetails;
import com.appl_maint_mngt.account.models.web.JwtToken;
import com.appl_maint_mngt.account.models.web.UserProfile;
import com.appl_maint_mngt.account.repositories.interfaces.IAccountUpdateableRepository;
import com.appl_maint_mngt.account.services.interfaces.IUserAuthService;
import com.appl_maint_mngt.common.errors.ErrorPayload;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.errors.interfaces.IErrorPayload;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.events.IEventBus;
import com.appl_maint_mngt.common.integration.IntegrationController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 07/04/2017.
 */

public class DummyUserAuthService implements IUserAuthService {

    @Override
    public void postLogin(ILoginForm form, IErrorCallback errorCallback) {
        IAccountUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getAccountRepository();
        IEventBus eventBus = ApplicationEventBus.getInstance();
        if(form.getPassword().equals("test1234")) {
            if(form.getEmail().equals("manager1@test.ie")) {
                JwtToken token = new JwtToken();
                token.setToken("1");
                repository.updateToken(token);
                eventBus.sendEvent(IUserAuthEvents.LOGIN_EVENT);
                return;
            } else if(form.getEmail().equals("tenant1@test.ie")) {
                JwtToken token = new JwtToken();
                token.setToken("2");
                repository.updateToken(token);
                eventBus.sendEvent(IUserAuthEvents.LOGIN_EVENT);
                return;
            } else if(form.getEmail().equals("engineer1@test.ie")) {
                JwtToken token = new JwtToken();
                token.setToken("3");
                repository.updateToken(token);
                eventBus.sendEvent(IUserAuthEvents.LOGIN_EVENT);
                return;
            }
        }
        errorCallback.callback(errorPayloadForLogin());
    }

    @Override
    public void getDetails(String token, IErrorCallback errorCallback) {
        IAccountUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getAccountRepository();
        IEventBus eventBus = ApplicationEventBus.getInstance();
        if(token.equals("1")) {
            AuthDetails details = new AuthDetails();
            details.setEmail("manager1@test.ie");
            details.setId((long) 1);
            details.setUserType(UserType.PROPERTY_MANAGER);
            repository.updateAuth(details);
            eventBus.sendEvent(IUserAuthEvents.AUTH_EVENT);
        } else if(token.equals("2")) {
            AuthDetails details = new AuthDetails();
            details.setEmail("tenant1@test.ie");
            details.setId((long) 2);
            details.setUserType(UserType.PROPERTY_TENANT);
            repository.updateAuth(details);
            eventBus.sendEvent(IUserAuthEvents.AUTH_EVENT);
        } else if(token.equals("3")) {
            AuthDetails details = new AuthDetails();
            details.setEmail("engineer1@test.ie");
            details.setId((long) 3);
            details.setUserType(UserType.MAINTENANCE_ENGINEER);
            repository.updateAuth(details);
            eventBus.sendEvent(IUserAuthEvents.AUTH_EVENT);
        }
    }

    private IErrorPayload errorPayloadForLogin() {
        Context context = MainActivity.getInstance();
        IErrorPayload errorPayload = new ErrorPayload();
        List<String> list = new ArrayList<>();
        list.add(context.getString(R.string.account_validation_login_invalid));
        errorPayload.setErrors(list);
        return errorPayload;
    }
}
