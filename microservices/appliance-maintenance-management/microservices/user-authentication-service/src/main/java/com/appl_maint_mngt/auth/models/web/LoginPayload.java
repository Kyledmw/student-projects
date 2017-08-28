package com.appl_maint_mngt.auth.models.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.appl_maint_mngt.auth.validation.EmailExists;

public class LoginPayload implements ILoginPayload {
	
	@NotNull
	@Email
	@EmailExists
	private String email;

	@NotNull
	@Size(min=6, max=30, message = "{login.password.size}")
	private String password;

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

}
