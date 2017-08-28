package com.appl_maint_mngt.auth.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserTypeValidator implements ConstraintValidator<UserType, String> {

	@Override
	public void initialize(UserType constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			com.appl_maint_mngt.auth.models.UserType.valueOf(value);
			return true;
		} catch(IllegalArgumentException iAE ) {
			return false;
		} catch(NullPointerException nE) {
			return false;
		}
	}

}
