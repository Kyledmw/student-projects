package com.appl_maint_mngt.tenant.pending.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.appl_maint_mngt.tenant.pending.models.constants.ResponseTypes;

public class ResponseTypeValidator implements ConstraintValidator<ResponseType, String> {

	@Override
	public void initialize(ResponseType constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			ResponseTypes.valueOf(value);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
