package com.appl_maint_mngt.auth.models.security;

import com.appl_maint_mngt.auth.models.roles.IRoleConstants;

public enum Scopes {
    REFRESH_TOKEN;
    
    public String authority() {
        return IRoleConstants.ROLE_PREFIX + this.name();
    }
}
