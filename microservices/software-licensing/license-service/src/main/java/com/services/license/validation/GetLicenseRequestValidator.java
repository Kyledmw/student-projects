package com.services.license.validation;

import java.util.ArrayList;
import java.util.List;

import com.services.license.constants.IAuthConstants;
import com.services.license.constants.IErrorMessages;
import com.services.license.validation.interfaces.IRequestValidator;

import io.vertx.ext.web.RoutingContext;

public class GetLicenseRequestValidator implements IRequestValidator {

    private boolean _valid;

    private List<String> _errMessages;

    public GetLicenseRequestValidator() {
        _valid = false;
        _errMessages = new ArrayList<>();
    }

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