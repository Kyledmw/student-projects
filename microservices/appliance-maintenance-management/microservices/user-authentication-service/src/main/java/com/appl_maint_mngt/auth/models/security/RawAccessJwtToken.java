package com.appl_maint_mngt.auth.models.security;

import org.springframework.security.authentication.BadCredentialsException;

import com.appl_maint_mngt.auth.security.constants.IAuthSecurityErrors;
import com.appl_maint_mngt.auth.security.exceptions.JwtExpiredTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class RawAccessJwtToken implements IJwtToken {

	private String token;
	
	public RawAccessJwtToken(String token) {
		this.token = token;
	}
	
	public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException(IAuthSecurityErrors.INVALID_JWT, ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredTokenException(this, IAuthSecurityErrors.JWT_EXPIRED, expiredEx);
        }
	}

	@Override
	public String getToken() {
		return token;
	}
	
	
}
