package com.voting_app.controllers.forms;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.voting_app.VotingAppApplication;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class ConstituencyFormTests {

	private static Validator _validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		_validator = factory.getValidator();
	}

	@Test
	public void nameIsNull() {
		final String validationMessage = "Name cannot be empty";

		ConstituencyForm cForm = new ConstituencyForm();
		cForm.setName(null);
		Set<ConstraintViolation<ConstituencyForm>> constraintViolations = _validator.validate(cForm);
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void nameIsTooShort() {
		final String shortName = "tp";
		final String validationMessage = "Please enter a name between 3 and 30";
				
		ConstituencyForm cForm = new ConstituencyForm();
		cForm.setName(shortName);
		Set<ConstraintViolation<ConstituencyForm>> constraintViolations = _validator.validate(cForm);
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	
	}

	@Test
	public void nameIsTooLong() {
		final String longName = "An example of a very long name ";
		final String validationMessage = "Please enter a name between 3 and 30";
				
		ConstituencyForm cForm = new ConstituencyForm();
		cForm.setName(longName);
		Set<ConstraintViolation<ConstituencyForm>> constraintViolations = _validator.validate(cForm);
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	
	}
}
