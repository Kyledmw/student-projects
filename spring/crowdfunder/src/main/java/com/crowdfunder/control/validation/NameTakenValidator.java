package com.crowdfunder.control.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crowdfunder.control.validation.annotations.NameTaken;
import com.crowdfunder.services.interfaces.IUserService;


/**
 ********************************************************************
 * Custom hibernate validator that checks if a name is already taken
 * 
 *
 * @author Joel Satkauskas
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class NameTakenValidator implements ConstraintValidator<NameTaken, String> {
	
	@Autowired
	private IUserService _userServ;
	
	@Override
	public void initialize(NameTaken constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (_userServ.findByUsername(value) == null)
		{
			return true;
		}
		return false;
	}

}
