package com.services.profile.validation;

import java.util.ArrayList;
import java.util.List;

import com.services.profile.constants.IAuthConstants;
import com.services.profile.constants.IErrorMessages;
import com.services.profile.constants.IRequestConstants;
import com.services.profile.validation.interfaces.IRequestValidator;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class SaveProfileRequestValidator implements IRequestValidator {

    private boolean _valid;

    private List<String> _errMessages;

    public SaveProfileRequestValidator() {
        _valid = false;
        _errMessages = new ArrayList<>();
    }


    @Override
    public void validate(RoutingContext routingContext) {
        _valid = true;
        _errMessages.clear();
        try {
            JsonObject requestBody = routingContext.getBodyAsJson();

            String token = requestBody.getString(IAuthConstants.TOKEN_KEY);
            String profileName = requestBody.getString(IRequestConstants.PROFILE_NAME);
            String companyName = requestBody.getString(IRequestConstants.COMPANY_NAME);

            if(token == null) {
                _errMessages.add(IErrorMessages.INVALID_TOKEN);
                _valid = false;
            }

            if(profileName == null) {
                _errMessages.add(IErrorMessages.EMPTY_PROFILE_NAME);
                _valid = false;
            }

            if(companyName == null) {
                _errMessages.add(IErrorMessages.EMPTY_COMPANY_NAME);
                _valid = false;
            }

        } catch (DecodeException e) {
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