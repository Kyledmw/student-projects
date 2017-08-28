package com.appl_maint_mngt.auth.security.jwt;

public interface ITokenExtractor {
    public String extract(String payload);
}
