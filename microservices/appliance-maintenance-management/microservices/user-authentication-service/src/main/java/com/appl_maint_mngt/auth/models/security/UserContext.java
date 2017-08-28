package com.appl_maint_mngt.auth.models.security;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import com.appl_maint_mngt.auth.security.constants.IAuthSecurityErrors;

public class UserContext {

	private final String email;
	private final List<GrantedAuthority> authorities;
	
	private UserContext(String email, List<GrantedAuthority> authorities) {
		this.email = email;
		this.authorities = authorities;
	}
	
	public static UserContext create(String username, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException(IAuthSecurityErrors.BLANK_EMAIL + username);
        return new UserContext(username, authorities);
	}
	
	public String getEmail() {
		return email;
	}
	
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	
}
