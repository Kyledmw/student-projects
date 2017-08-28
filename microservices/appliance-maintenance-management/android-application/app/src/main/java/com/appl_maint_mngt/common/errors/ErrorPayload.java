package com.appl_maint_mngt.common.errors;

import com.appl_maint_mngt.common.errors.interfaces.IErrorPayload;

import java.util.List;

/**
 * Created by Kyle on 07/04/2017.
 */

public class ErrorPayload implements IErrorPayload {

    private List<String> errors;

    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
