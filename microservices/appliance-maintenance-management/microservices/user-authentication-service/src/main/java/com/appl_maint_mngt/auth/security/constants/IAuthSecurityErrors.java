package com.appl_maint_mngt.auth.security.constants;

public interface IAuthSecurityErrors {

	String AUTH_HEADER_BLANK_ERR = "Authorization header cannot be blank.";
	String INV_AUTH_HEADER_SZ =  "Invalid authorization header size.";
	
	String INVALID_JWT = "Invalid JWT Token: ";
	String JWT_EXPIRED = "JWT Token expired.";
	
	String BLANK_EMAIL = "Email is blank: ";
	
	String CANNOT_TRUST_TOKEN = "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead";
	
	String UNAUTH_ERR = "Unauthorized";
	
	String CANNOT_CREATE_JWT_EMAIL = "Cannot create JWT Token without email";
	String USER_NO_PRIVILEGES = "User doesn't have any privileges";
	
	String AUTH_FAILED = "Authentication failed.";
	String INVALID_DETAILS = "Invalid username or password.";
}
