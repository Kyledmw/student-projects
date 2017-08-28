package com.appl_maint_mngt.maintenance.schedule.pending.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SchedulerTypeValidator implements ConstraintValidator<SchedulerType, String> {

	@Override
	public void initialize(SchedulerType constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try{
			com.appl_maint_mngt.maintenance.schedule.pending.models.constants.SchedulerType.valueOf(value);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
