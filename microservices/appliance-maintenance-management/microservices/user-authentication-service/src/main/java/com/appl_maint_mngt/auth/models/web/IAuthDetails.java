package com.appl_maint_mngt.auth.models.web;

import com.appl_maint_mngt.auth.models.UserType;

public interface IAuthDetails {
	Long getId();
	String getEmail();
	UserType getUserType();
}
