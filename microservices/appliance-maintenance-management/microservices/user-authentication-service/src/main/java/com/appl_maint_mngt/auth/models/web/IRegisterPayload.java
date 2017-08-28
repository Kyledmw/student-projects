package com.appl_maint_mngt.auth.models.web;

public interface IRegisterPayload {

	String getEmail();
	String getPassword();
	String getPasswordConfirmation();
	String getUserType();
	
	void setEmail(String email);
	void setPassword(String password);
	void setPasswordConfirmation(String passwordConfirmation);
	void setUserType(String userType);
}
