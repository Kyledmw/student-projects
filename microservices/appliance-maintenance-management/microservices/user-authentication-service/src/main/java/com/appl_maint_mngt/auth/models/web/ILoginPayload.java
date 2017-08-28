package com.appl_maint_mngt.auth.models.web;

public interface ILoginPayload {

	String getEmail();
	String getPassword();
	
	void setEmail(String email);
	void setPassword(String password);
}
