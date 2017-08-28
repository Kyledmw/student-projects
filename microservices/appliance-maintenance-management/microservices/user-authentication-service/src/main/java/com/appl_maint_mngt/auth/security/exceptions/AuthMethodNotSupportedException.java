package com.appl_maint_mngt.auth.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class AuthMethodNotSupportedException extends AuthenticationServiceException {

	private static final long serialVersionUID = 1L;

	public AuthMethodNotSupportedException(String msg) {
		super(msg);
	}

}