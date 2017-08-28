package com.appl_maint_mngt.auth.services;

import org.springframework.security.authentication.InsufficientAuthenticationException;

import com.appl_maint_mngt.auth.models.IUserAuthReadable;
import com.appl_maint_mngt.auth.models.security.IJwtToken;
import com.appl_maint_mngt.auth.models.security.RefreshToken;
import com.appl_maint_mngt.auth.security.exceptions.InvalidJwtTokenException;

public interface IJwtService {

	IJwtToken getTokenForUser(IUserAuthReadable user) throws InsufficientAuthenticationException;
	
	RefreshToken getRefreshToken(String payload) throws InvalidJwtTokenException;
}
