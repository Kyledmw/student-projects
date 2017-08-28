package com.appl_maint_mngt.auth.models.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.Claims;

public class AccessJwtToken implements IJwtToken {
	
	private final String rawToken;
	
	private Claims claims;
	
	public AccessJwtToken(final String token, Claims claims) {
		this.rawToken = token;
		this.claims = claims;
	}

	@Override
	public String getToken() {
		return rawToken;
	}
	
	@JsonIgnore
	public Claims getClaims() {
		return claims;
	}

}
