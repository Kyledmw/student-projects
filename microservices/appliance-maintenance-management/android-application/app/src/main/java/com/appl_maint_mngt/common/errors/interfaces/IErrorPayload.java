package com.appl_maint_mngt.common.errors.interfaces;

import java.util.List;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IErrorPayload {
    List<String> getErrors();
    void setErrors(List<String> errors);
}
