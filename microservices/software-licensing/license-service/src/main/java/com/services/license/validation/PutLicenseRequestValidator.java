package com.services.license.validation;

import java.util.ArrayList;
import java.util.List;

import com.services.license.constants.IAuthConstants;
import com.services.license.constants.IErrorMessages;
import com.services.license.constants.IRequestConstants;
import com.services.license.validation.interfaces.IRequestValidator;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class PutLicenseRequestValidator implements IRequestValidator {

    private boolean _valid;

    private List<String> _errMessages;

    public PutLicenseRequestValidator() {
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
            String softwareID = requestBody.getString(IRequestConstants.SOFTWARE_ID_KEY);

            if(token == null) {
                _errMessages.add(IErrorMessages.INVALID_TOKEN);
                _valid = false;
            }

            if(softwareID == null) {
                _errMessages.add(IErrorMessages.EMPTY_SOFTWARE_ID);
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
