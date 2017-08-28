package com.appl_maint_mngt.property.manager.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appl_maint_mngt.property.manager.services.IPropertyManagerService;

@Component
public class ManagerExistsValidator implements ConstraintValidator<ManagerExists, Long> {
	
	@Autowired
	private IPropertyManagerService propMngServ;

	@Override
	public void initialize(ManagerExists constraintAnnotation) {
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if(value == null) return false;
		return propMngServ.managerExists(value);
	}

}
