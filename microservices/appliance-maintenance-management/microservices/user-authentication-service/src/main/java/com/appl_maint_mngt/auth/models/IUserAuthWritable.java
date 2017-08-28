package com.appl_maint_mngt.auth.models;

import java.util.List;

import com.appl_maint_mngt.auth.models.roles.UserRole;

public interface IUserAuthWritable {

	void setId(Long id);
	void setEmail(String email);
	void setPassword(String password);
	void setUserType(UserType userType);
	void setRoles(List<UserRole> roles);
}
