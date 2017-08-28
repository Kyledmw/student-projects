package com.appl_maint_mngt.auth.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appl_maint_mngt.auth.models.IUserAuthReadable;
import com.appl_maint_mngt.auth.services.IUserAuthService;

@Component
public class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {
	
	@Autowired
	private IUserAuthService authServ;

	@Override
	public void initialize(EmailExists constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		IUserAuthReadable user = authServ.getByEmail(value);
		return user != null;
	}

}
