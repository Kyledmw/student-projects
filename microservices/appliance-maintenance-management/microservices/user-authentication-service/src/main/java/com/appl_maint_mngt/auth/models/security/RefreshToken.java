package com.appl_maint_mngt.auth.models.security;

import java.util.List;

import com.appl_maint_mngt.auth.security.jwt.IJwtConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@SuppressWarnings("unchecked")
public class RefreshToken implements IJwtToken {
    private Jws<Claims> claims;
    
    public RefreshToken(Jws<Claims> claims) {
    	this.claims = claims;
    }
    
    public static RefreshToken create(RawAccessJwtToken token, String signingKey) {
    	
    	Jws<Claims> claims = token.parseClaims(signingKey);
    	
    	List<String> scopes = claims.getBody().get(IJwtConstants.SCOPES, List.class);        
    	if (scopes == null || scopes.isEmpty()) {
            return null;
        }
        return new RefreshToken(claims);
    }
    

	@Override
	public String getToken() {
		return null;
	}
	
	public Jws<Claims> getClaims() {
		return claims;
	}
	
	public String getJti() {
		return claims.getBody().getId();
	}
	
	public String getSubject() {
		return claims.getBody().getSubject();
	}

}
