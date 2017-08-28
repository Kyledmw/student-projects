package com.appl_maint_mngt.common.validation;

import com.appl_maint_mngt.common.validation.interfaces.IValidatorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 15/03/2017.
 */
public class GenericValidatorResponse implements IValidatorResponse {

    private List<String> errors;
    private boolean valid;

    public GenericValidatorResponse(boolean valid) {
        this(valid, new ArrayList<String>());
    }

    public GenericValidatorResponse(boolean valid, List<String> errors) {
        this.errors = errors;
        this.valid = valid;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}
