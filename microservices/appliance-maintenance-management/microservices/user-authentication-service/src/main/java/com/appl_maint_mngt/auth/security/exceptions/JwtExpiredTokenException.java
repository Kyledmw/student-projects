package com.appl_maint_mngt.auth.security.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.appl_maint_mngt.auth.models.security.IJwtToken;

public class JwtExpiredTokenException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	private IJwtToken token;

	public JwtExpiredTokenException(String msg) {
		super(msg);
	}
	
	public JwtExpiredTokenException(IJwtToken token, String msg, Throwable t) {
		super(msg, t);
		this.token = token;
	}

	public String token() {
		return token.getToken();
	}
}
