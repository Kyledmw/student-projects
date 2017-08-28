package com.appl_maint_mngt.auth.models.web;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.appl_maint_mngt.auth.validation.UniqueEmail;
import com.appl_maint_mngt.auth.validation.UserType;

public class RegisterPayload implements IRegisterPayload {

	@NotNull
	@Email
	@UniqueEmail
	private String email;

	@NotNull
	@Size(min=6, max=30, message = "{register.password.size}")
	private String password;

	@NotNull
	private String passwordConfirmation;

	@NotNull
	@UserType
	private String userType;
	
	private boolean passwordEqual;
	
	@AssertTrue(message = "{register.password.equal}")
	public boolean getPasswordEqual() {
		if(password == null || passwordConfirmation == null) {
			return false;
		}
		passwordEqual = this.password.equals(this.passwordConfirmation);
		return passwordEqual;
	}
	
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	@Override
	public String getUserType() {
		return userType;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	@Override
	public void setUserType(String userType) {
		this.userType = userType;
	}

}
