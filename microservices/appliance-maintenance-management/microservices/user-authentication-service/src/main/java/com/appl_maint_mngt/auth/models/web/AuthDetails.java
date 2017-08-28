package com.appl_maint_mngt.auth.models.web;

import com.appl_maint_mngt.auth.models.IUserAuthReadable;
import com.appl_maint_mngt.auth.models.UserType;

public class AuthDetails implements IAuthDetails {
	
	private Long id;
	private String email;
	private UserType type;
	
	public AuthDetails(IUserAuthReadable userAuth) {
		id = userAuth.getId();
		email = userAuth.getEmail();
		type = userAuth.getUserType();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public UserType getUserType() {
		return type;
	}

}
