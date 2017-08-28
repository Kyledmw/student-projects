package com.appl_maint_mngt.account.validation;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.forms.LoginForm;
import com.appl_maint_mngt.account.forms.interfaces.ILoginForm;
import com.appl_maint_mngt.account.validation.constants.IAccountValidationConstants;
import com.appl_maint_mngt.account.validation.interfaces.ILoginFormValidator;
import com.appl_maint_mngt.common.validation.GenericValidatorResponse;
import com.appl_maint_mngt.common.validation.interfaces.IValidatorResponse;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 07/04/2017.
 */
public class LoginFormValidator implements ILoginFormValidator {
    private static final Logger logger = LoggerManager.getLogger(LoginFormValidator.class);

    private Context cntxt;

    public LoginFormValidator() {
        cntxt = MainActivity.getInstance();
    }

    @Override
    public IValidatorResponse validate(ILoginForm form) {
        logger.i("Validating LoginForm for email: %s", form.getEmail());
        List<String> errors = new ArrayList<>();

        if(!form.getEmail().matches(IAccountValidationConstants.EMAIL_REGEX)) errors.add(cntxt.getString(R.string.account_validation_email_valid));
        if(form.getPassword().length() < IAccountValidationConstants.PASSWORD_MIN) errors.add(cntxt.getString(R.string.account_validation_password_min, IAccountValidationConstants.PASSWORD_MIN));
        if(form.getPassword().length() > IAccountValidationConstants.PASSWORD_MAX) errors.add(cntxt.getString(R.string.account_validation_password_max, IAccountValidationConstants.PASSWORD_MAX));

        return new GenericValidatorResponse(errors.size() == 0, errors);
    }
}
