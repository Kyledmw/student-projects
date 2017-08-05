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
public class DeleteCandidateFormTests {

	private static Validator _validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		_validator = factory.getValidator();
	}
	
	@Test
	public void invalidId() {
		final int invalidId = 0;
		final String validationMessage = "must be greater than or equal to 1";
		
		DeleteCandidateForm cForm = new DeleteCandidateForm();
		cForm.setId(invalidId);
		Set<ConstraintViolation<DeleteCandidateForm>> constraintViolations = _validator.validate(cForm);
		
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	}

}
