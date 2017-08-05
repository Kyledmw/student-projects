package com.crowdfunder.control.forms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class ProjectFormTests {
	
	private static final String VALID_PROJ_NAME = "Valid Project Name";
	private static final String VALID_DESC = "This is a valid description.";
	private static final BigDecimal VALID_GOAL = new BigDecimal(50000);
	private static final long VALID_DURATION = 20;
	private static final String VALID_YTUBE_LINK = "https://www.youtube.com/watch?v=tJKAQggSs9Q";
	
	private static Validator _validator;
	
	public static ProjectForm createValidForm() {
		MockMultipartFile firstFile = new MockMultipartFile("imageName", "filename.txt", "text/plain", "some xml".getBytes());
		ProjectForm projForm = new ProjectForm();
		projForm.setProjName(VALID_PROJ_NAME);
		projForm.setDesc(VALID_DESC);
		projForm.setGoalAmount(VALID_GOAL);
		projForm.setDuration(VALID_DURATION);
		projForm.setYTubeLink(VALID_YTUBE_LINK);
		projForm.setImage(firstFile);
		return projForm;
	}
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		_validator = factory.getValidator();
	}
	
	@Test
	public void valid() {
		final int expectedViolations = 0;
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(createValidForm());
		assertEquals(expectedViolations, constraintViolations.size());
	}
	
	@Test
	public void projNameIsNull() {
		final String nullProjName = null;
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setProjName(nullProjName);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_PROJNAME_NULL, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void projNameShort() {
		final String shortProjName = "KL";
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setProjName(shortProjName);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_PROJNAME_SIZE, constraintViolations.iterator().next().getMessage());
		
	}
	
	@Test
	public void projNameLong() {
		final String longProjName = "KLOII49EriKLOII49EriKLOII49EriQ";
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setProjName(longProjName);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_PROJNAME_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void descNull() {
		final String desc = null;
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setDesc(desc);

		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_DESC_NULL, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void descShort() {
		final String desc = "invalid";
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setDesc(desc);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_DESC_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void descLong() {
		final String desc = "vqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJbvqHtAGQlMtbTPgUHIwJb";
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setDesc(desc);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECTFORM_DESC_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void yTubeLinkNull() {
		final String yTubeLink = null;
		final int expectedViolations = 2;
		
		ProjectForm projForm = createValidForm();
		projForm.setYTubeLink(yTubeLink);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());

		List<String> messages = new ArrayList<String>();
		Iterator<ConstraintViolation<ProjectForm>> iter = constraintViolations.iterator();
		while(iter.hasNext()) {
			messages.add(iter.next().getMessage());
		}
		boolean correctViolation = messages.get(0).equals(IValidationStrings.PROJECTFORM_YTUBELINK_NULL) || messages.get(1).equals(IValidationStrings.PROJECTFORM_YTUBELINK_NULL);
		assertTrue(correctViolation);
	}
	
	@Test
	public void yTubeLinkValid() {
		final String yTubeLink = "https://www.youtube.com/";
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setYTubeLink(yTubeLink);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.CUSTOM_YTUBE_VALID, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void goalAmountLow() {
		final BigDecimal goalAmount = new BigDecimal(900);
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setGoalAmount(goalAmount);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECT_FORM_GOALAMOUNT_MINMAX, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void goalAmountHigh() {
		final BigDecimal goalAmount = new BigDecimal(110000);
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setGoalAmount(goalAmount);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECT_FORM_GOALAMOUNT_MINMAX, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void durationLow() {
		final long duration = 6;
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setDuration(duration);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECT_FORM_DURATION_MINMAX, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void durationHigh() {
		final long duration = 31;
		final int expectedViolations = 1;
		
		ProjectForm projForm = createValidForm();
		projForm.setDuration(duration);
		
		Set<ConstraintViolation<ProjectForm>> constraintViolations = _validator.validate(projForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.PROJECT_FORM_DURATION_MINMAX, constraintViolations.iterator().next().getMessage());
	}
}
