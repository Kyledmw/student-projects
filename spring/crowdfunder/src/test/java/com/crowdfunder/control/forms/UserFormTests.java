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
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class UserFormTests {
	
	private static final String VALID_USERNAME = "Username";
	private static final String VALID_PASSWORD = "password";
	private static final String VALID_EMAIL = "email@email.com";
	private static final BigDecimal VALID_CREDIT_LIMIT = new BigDecimal(2000);

	private static Validator _validator;
	
	public static UserForm createValidForm() {
		UserForm userForm = new UserForm();
		userForm.setEmail(VALID_EMAIL);
		userForm.setUserName(VALID_USERNAME);
		userForm.setPassword(VALID_PASSWORD);
		userForm.setPasswordConfirm(VALID_PASSWORD);
		userForm.setCreditLimit(VALID_CREDIT_LIMIT);
		return userForm;
	}
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		_validator = factory.getValidator();
	}
	
	@Test
	public void valid() {
		final int expectedViolations = 0;
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(createValidForm());
		assertEquals(expectedViolations, constraintViolations.size());
	}
	
	@Test
	public void userNameNull() {
		final String userName = null;
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setUserName(userName);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(userForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_USERNAME_NULL, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void userNameShort() {
		final String userName = "KL";
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setUserName(userName);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(userForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_USERNAME_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void userNameLong() {
		final String userName = "KLOII49EriKLOII49EriKLOII49EriQ";
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setUserName(userName);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(userForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_USERNAME_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void passwordNull() {
		final String password = null;
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setPassword(password);
		
		UserForm ufSpy = Mockito.spy(userForm);
		Mockito.when(ufSpy.getPasswordEqual()).thenReturn(true);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(ufSpy);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_PASSWORD_NULL, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void passwordShort() {
		final String password = "qwer";
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setPassword(password);
		
		UserForm ufSpy = Mockito.spy(userForm);
		Mockito.when(ufSpy.getPasswordEqual()).thenReturn(true);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(ufSpy);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_PASSWORD_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void passwordLong() {
		final String password = "KLOII49EriKLOII49EriKLOII49EriQ";
		final int expectedViolations = 2;
		
		UserForm userForm = createValidForm();
		userForm.setPassword(password);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(userForm);
		assertEquals(expectedViolations, constraintViolations.size());
		List<String> messages = new ArrayList<>();
		Iterator<ConstraintViolation<UserForm>> iter = constraintViolations.iterator();
		while(iter.hasNext()) {
			messages.add(iter.next().getMessage());
		}
		boolean correctViolation = messages.get(0).equals(IValidationStrings.USERFORM_PASSWORD_SIZE) || messages.get(1).equals(IValidationStrings.USERFORM_PASSWORD_SIZE);
		assertTrue(correctViolation);
	}
	
	@Test
	public void passwordConfirmNull() {
		final String passwordConfirm = null;
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setPasswordConfirm(passwordConfirm);
		
		UserForm ufSpy = Mockito.spy(userForm);
		Mockito.when(ufSpy.getPasswordEqual()).thenReturn(true);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(ufSpy);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_PASSWORDCONFIRM_NULL, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void passwordConfirmShort() {
		final String passwordConfirm = "qwe";
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setPasswordConfirm(passwordConfirm);
		
		UserForm ufSpy = Mockito.spy(userForm);
		Mockito.when(ufSpy.getPasswordEqual()).thenReturn(true);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(ufSpy);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_PASSWORDCONFIRM_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void passwordConfirmLong() {
		final String passwordConfirm = "KLOII49EriKLOII49EriKLOII49EriQ";
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setPasswordConfirm(passwordConfirm);
		
		UserForm ufSpy = Mockito.spy(userForm);
		Mockito.when(ufSpy.getPasswordEqual()).thenReturn(true);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(ufSpy);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_PASSWORDCONFIRM_SIZE, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void emailNull() {
		final String email = null;
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setEmail(email);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(userForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_EMAIL_NULL, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void creditLimitShort() {
		final BigDecimal creditLimit = new BigDecimal(49);
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setCreditLimit(creditLimit);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(userForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_CREDITLIMIT_MINMAX, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void creditLimitLong() {
		final BigDecimal creditLimit = new BigDecimal(5001);
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setCreditLimit(creditLimit);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(userForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_CREDITLIMIT_MINMAX, constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	public void passwordsUnEqual() {
		final String password = "validpw";
		final String passwordConfirm = "validpwconf";
		final int expectedViolations = 1;
		
		UserForm userForm = createValidForm();
		userForm.setPassword(password);
		userForm.setPasswordConfirm(passwordConfirm);
		
		Set<ConstraintViolation<UserForm>> constraintViolations = _validator.validate(userForm);
		assertEquals(expectedViolations, constraintViolations.size());
		assertEquals(IValidationStrings.USERFORM_PASSWORD_CONFIRM, constraintViolations.iterator().next().getMessage());
	
	}
}
