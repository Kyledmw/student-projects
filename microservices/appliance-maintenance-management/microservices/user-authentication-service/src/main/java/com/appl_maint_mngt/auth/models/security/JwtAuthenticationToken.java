package com.appl_maint_mngt.auth.models.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.appl_maint_mngt.auth.security.constants.IAuthSecurityErrors;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	private RawAccessJwtToken rawAccessToken;
	private UserContext userContext;
	
	public JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
		super(null);
		rawAccessToken = unsafeToken;
	}

	public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.userContext = userContext;
		super.setAuthenticated(true);
	}
	
	@Override
	public void setAuthenticated(boolean authenticated) {
		if(authenticated) {
			throw new IllegalArgumentException(IAuthSecurityErrors.CANNOT_TRUST_TOKEN);
		}
        super.setAuthenticated(false);
	}
	
	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		rawAccessToken = null;
	}

	@Override
	public Object getCredentials() {
		return rawAccessToken;
	}

	@Override
	public Object getPrincipal() {
		return userContext;
	}

}
