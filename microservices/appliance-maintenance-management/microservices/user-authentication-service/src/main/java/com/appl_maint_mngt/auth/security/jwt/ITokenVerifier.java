package com.appl_maint_mngt.auth.security.jwt;

public interface ITokenVerifier {

	public boolean verify(String jti);
}
