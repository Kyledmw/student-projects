package com.appl_maint_mngt.auth.models;

import java.util.List;

import com.appl_maint_mngt.auth.models.roles.UserRole;

public interface IUserAuthReadable {

	Long getId();
	String getEmail();
	String getPassword();
	UserType getUserType();
	List<UserRole> getRoles();
}
