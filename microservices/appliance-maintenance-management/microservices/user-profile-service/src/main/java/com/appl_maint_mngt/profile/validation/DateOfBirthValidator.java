package com.appl_maint_mngt.profile.validation;

import java.sql.Timestamp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.DateTime;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirth, Timestamp> {
	
	private static final int MIN_YEAR = 1940;
	private static final int MAX_YEAR = 1995;

	@Override
	public void initialize(DateOfBirth constraintAnnotation) {
	}

	@Override
	public boolean isValid(Timestamp value, ConstraintValidatorContext context) {
		DateTime min = new DateTime().withYear(MIN_YEAR);
		DateTime max = new DateTime().withYear(MAX_YEAR);
		long minMillis = min.getMillis();
		long maxMillis = max.getMillis();
		long dobMillis = new DateTime(value).getMillis();
		return (dobMillis >= minMillis && dobMillis <= maxMillis);
	}

}
