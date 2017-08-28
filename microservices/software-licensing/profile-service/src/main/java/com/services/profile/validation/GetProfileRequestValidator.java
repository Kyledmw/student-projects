package com.services.profile.validation;

import java.util.ArrayList;
import java.util.List;

import com.services.profile.constants.IAuthConstants;
import com.services.profile.constants.IErrorMessages;
import com.services.profile.validation.interfaces.IRequestValidator;

import io.vertx.ext.web.RoutingContext;

public class GetProfileRequestValidator implements IRequestValidator {

    private boolean _valid;

    private List<String> _errMessages;


    public GetProfileRequestValidator() {
        _valid = false;
        _errMessages = new ArrayList<>();
    }

    @Override
    public void validate(RoutingContext routingContext) {
        _valid = true;
        _errMessages.clear();
        String token = routingContext.request().getParam(IAuthConstants.TOKEN_KEY);
        if(token == null) {
            _errMessages.add(IErrorMessages.INVALID_TOKEN);
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