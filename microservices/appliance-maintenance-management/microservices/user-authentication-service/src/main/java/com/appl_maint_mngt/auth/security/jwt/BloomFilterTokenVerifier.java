package com.appl_maint_mngt.auth.security.jwt;

import org.springframework.stereotype.Component;

@Component
public class BloomFilterTokenVerifier implements ITokenVerifier {

	@Override
	public boolean verify(String jti) {
		return true;
	}

}
