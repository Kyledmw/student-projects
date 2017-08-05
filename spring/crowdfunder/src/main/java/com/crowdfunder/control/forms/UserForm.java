package com.crowdfunder.control.forms;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.crowdfunder.control.validation.annotations.NameTaken;
import com.crowdfunder.control.validation.annotations.ValidEmail;

/**
 ********************************************************************
 * Class representing a form for creating a new project.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class UserForm {;

	@Valid
	@NameTaken
	@NotNull(message = "{userForm.userName.null}")
	@Size(min=3, max=30, message = "{userForm.userName.size}")
	private String userName;

	@Valid
	@NotNull(message = "{userForm.password.null}")
	@Size(min=6, max=30, message = "{userForm.password.size}")
	private String password;

	@Valid
	@NotNull(message = "{userForm.passwordConfirm.null}")
	@Size(min=6, max=30, message = "{userForm.passwordConfirm.size}")
	private String passwordConfirm;

	@NotNull(message = "{userform.email.null}")
	@Email
	@ValidEmail
	private String email;
	
	@DecimalMax(value = "5000", message = "{userForm.creditLimit.minMax}")
	@DecimalMin(value = "50",  message = "{userForm.creditLimit.minMax}")
	private BigDecimal creditLimit;
	
	private boolean passwordEqual;

	@AssertTrue(message = "{userform.password.confirm}")
	public boolean getPasswordEqual() {
		if(password == null || passwordConfirm == null) {
			return false;
		}
		passwordEqual = this.password.equals(this.passwordConfirm);
		return passwordEqual;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String username) {
		userName = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
}
