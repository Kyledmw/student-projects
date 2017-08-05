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
import org.springframework.beans.factory.annotation.Autowired;
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
public class VoteFormTests {

	@Autowired
	private static Validator _validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		_validator = factory.getValidator();
	}
	
	@Test
	public void votesAreValid() {
		final String validationMessage = "Please order votes sequentially"; 
		
		VoteForm vForm = new VoteForm();
		vForm.setVotes(null);
		
		Set<ConstraintViolation<VoteForm>> constraintViolations = _validator.validate(vForm);
		assertEquals(1, constraintViolations.size());
		assertEquals(validationMessage, constraintViolations.iterator().next().getMessage());
	}

}
