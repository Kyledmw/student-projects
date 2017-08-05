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
public class CandidateFormTest {

	private static Validator _validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		_validator = factory.getValidator();
	}

	@Test
	public void nameIsNull() {
		final int validId = 1;
		final String validationMessage = "Name cannot be empty";

		CandidateForm cForm = new CandidateForm();
		cForm.setName(null);
		cForm.setPoliticalPartyId(validId);
		cForm.setConstituencyId(validId);
		Set<ConstraintViolation<CandidateForm>> constraintViolations = _validator.validate(cForm);
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void nameIsTooShort() {
		final int validId = 1;
		final String shortName = "tp";
		final String validationMessage = "Please enter a name between 3 and 30";
				
		CandidateForm cForm = new CandidateForm();
		cForm.setName(shortName);
		cForm.setPoliticalPartyId(validId);
		cForm.setConstituencyId(validId);
		Set<ConstraintViolation<CandidateForm>> constraintViolations = _validator.validate(cForm);
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	
	}

	@Test
	public void nameIsTooLong() {
		final int validId = 1;
		final String longName = "An example of a very long name ";
		final String validationMessage = "Please enter a name between 3 and 30";
				
		CandidateForm cForm = new CandidateForm();
		cForm.setName(longName);
		cForm.setPoliticalPartyId(validId);
		cForm.setConstituencyId(validId);
		Set<ConstraintViolation<CandidateForm>> constraintViolations = _validator.validate(cForm);
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	
	}

	@Test
	public void invalidConstituencyId() {
		final int validId = 1;
		final int invalidId = 0;
		final String name = "Kyle";
		final String validationMessage = "must be greater than or equal to 1";
				
		CandidateForm cForm = new CandidateForm();
		cForm.setName(name);
		cForm.setPoliticalPartyId(validId);
		cForm.setConstituencyId(invalidId);
		Set<ConstraintViolation<CandidateForm>> constraintViolations = _validator.validate(cForm);

		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	
	}

	@Test
	public void invalidPoliticalPartyId() {
		final int validId = 1;
		final int invalidId = 0;
		final String name = "Kyle";
		final String validationMessage = "must be greater than or equal to 1";
				
		CandidateForm cForm = new CandidateForm();
		cForm.setName(name);
		cForm.setPoliticalPartyId(invalidId);
		cForm.setConstituencyId(validId);
		Set<ConstraintViolation<CandidateForm>> constraintViolations = _validator.validate(cForm);
		
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	

	}
}
