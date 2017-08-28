package com.appl_maint_mngt.auth.models.roles;

public enum Role {
	ADMIN, MEMBER;
	
    public String authority() {
        return IRoleConstants.ROLE_PREFIX + this.name();
    }
}
