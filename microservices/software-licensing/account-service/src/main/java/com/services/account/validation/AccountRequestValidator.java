package com.services.account.validation;

import java.util.ArrayList;
import java.util.List;

import com.services.account.constants.IRequestConstants;
import com.services.account.strings.IErrorMessages;
import com.services.account.validation.interfaces.IRequestValidator;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class AccountRequestValidator implements IRequestValidator {

    private boolean _valid;

    private List<String> _errMessages;

    public AccountRequestValidator() {
        _valid = false;
        _errMessages = new ArrayList<>();
    }

    @Override
    public void validate(RoutingContext routingContext) {
        _valid = true;
        _errMessages.clear();

        try {
            JsonObject userDetails = routingContext.getBodyAsJson();

            String username = userDetails.getString(IRequestConstants.USERNAME_KEY);
            String password = userDetails.getString(IRequestConstants.PASSWORD_KEY);

            if(username == null) {
                _errMessages.add(IErrorMessages.EMPTY_USERNAME);
                _valid = false;
            }

            if(password == null) {
                _errMessages.add(IErrorMessages.EMPTY_PASSWORD);
                _valid = false;
            }

        } catch(DecodeException e) {
            _errMessages.add(IErrorMessages.CORRUPTED_DATA);
            _valid = false;
        }
    }

    @Override
    public boolean isValid() {
        return _valid;
    }

    @Override
    public List<String> getErrorMessages() {
        return _errMessages;
    }
}

