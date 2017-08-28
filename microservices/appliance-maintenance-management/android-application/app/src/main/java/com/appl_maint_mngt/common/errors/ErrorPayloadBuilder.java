package com.appl_maint_mngt.common.errors;

import com.appl_maint_mngt.common.errors.interfaces.IErrorPayload;
import com.appl_maint_mngt.common.errors.interfaces.IErrorPayloadBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 16/04/2017.
 */

public class ErrorPayloadBuilder implements IErrorPayloadBuilder  {
    @Override
    public IErrorPayload buildForString(String error) {
        ErrorPayload payload = new ErrorPayload();
        List<String> errors = new ArrayList<>();
        errors.add(error);
        payload.setErrors(errors);
        return payload;
    }
}
