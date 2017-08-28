package com.appl_maint_mngt.common.validation.interfaces;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IValidator<T> {

    IValidatorResponse validate(T obj);
}
