package com.appl_maint_mngt.account.models.web;

/**
 * Created by Kyle on 17/03/2017.
 */

public class JwtToken implements IJwtToken {

    private String token;

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
