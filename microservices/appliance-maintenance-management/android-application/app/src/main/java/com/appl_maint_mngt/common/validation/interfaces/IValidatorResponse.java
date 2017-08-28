package com.appl_maint_mngt.common.validation.interfaces;

import java.util.List;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IValidatorResponse {

    List<String> getErrors();

    boolean isValid();
}
