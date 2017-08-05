package com.crowdfunder.control.forms;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class EditProjectFormTests {
	
	private static final String VALID_DESC = "This is a valid description.";

	private static Validator _validator;
	
	public static EditProjectForm createValidForm() {
		EditProjectForm form = new EditProjectForm();
		form.setDesc(VALID_DESC);
		return form;
	}
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		_validator = factory.getValidator();
	}
	
	@Test
	public void valid() {
		final int expectedViolations = 0;
		Set<ConstraintViolation<EditProjectForm>> constraintViolations = _validator.validate(createValidForm());
		assertEquals(expectedViolations, constraintViolations.size());
	}

	@Test
	public void descNull() {
		final String desc = null;
		final int expectedViolations = 1;
		
		EditProjectForm form = createValidForm();
		form.setDesc(desc);

		Set<ConstraintViolation<EditProjectForm>> constraintViolations = _validator.validate(form);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_DESC_NULL, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void descShort() {
		final String desc = "invalid";
		final int expectedViolations = 1;
		
		EditProjectForm form = createValidForm();
		form.setDesc(desc);
		
		Set<ConstraintViolation<EditProjectForm>> constraintViolations = _validator.validate(form);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_DESC_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void descLong() {
		final String desc = "vqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJb";
		final int expectedViolations = 1;
		
		EditProjectForm form = createValidForm();
		form.setDesc(desc);
		
		Set<ConstraintViolation<EditProjectForm>> constraintViolations = _validator.validate(form);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_DESC_SIZE, constraintViolations.iterator().next().getMessage());
	}
}
