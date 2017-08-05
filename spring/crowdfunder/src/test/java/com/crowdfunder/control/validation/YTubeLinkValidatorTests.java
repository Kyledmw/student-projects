package com.crowdfunder.control.validation;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.control.validation.annotations.ValidYTubeLink;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class YTubeLinkValidatorTests {

	private static final String VALID_YTUBE_LINK = "https://www.youtube.com/watch?v=tJKAQggSs9Q";
	private static final String INVALID_YTUBE_LINK = "https://www.youtube.com/watch";
	
	private static Validator _validator;
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		_validator = factory.getValidator();
	}
	
	@Test
	public void valid() {
		final int expectedViolations = 0;
		
		DummyForm form = new DummyForm();
		form.setYTubeLink(VALID_YTUBE_LINK);
		
		Set<ConstraintViolation<DummyForm>> constraintViolations = _validator.validate(form);
		assertEquals(expectedViolations, constraintViolations.size());
	}
	
	@Test
	public void invalid() {
		final int expectedViolations = 1;
		
		DummyForm form = new DummyForm();
		form.setYTubeLink(INVALID_YTUBE_LINK);
		
		Set<ConstraintViolation<DummyForm>> constraintViolations = _validator.validate(form);
		assertEquals(expectedViolations, constraintViolations.size());
	}
	
	@Test
	public void nullLink() {
		final int expectedViolations = 1;
		
		DummyForm form = new DummyForm();
		form.setYTubeLink(null);
		
		Set<ConstraintViolation<DummyForm>> constraintViolations = _validator.validate(form);
		assertEquals(expectedViolations, constraintViolations.size());
	}
	
	private class DummyForm {
		
		@ValidYTubeLink
		private String _yTubeLink;
		
		@SuppressWarnings("unused")
		public String getYTubeLink() {
			return _yTubeLink;
		}
		
		public void setYTubeLink(String link) {
			_yTubeLink = link;
		}
	}
}
