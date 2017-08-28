package com.appl_maint_mngt.auth.security.jwt;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appl_maint_mngt.auth.config.security.JwtConfig;
import com.appl_maint_mngt.auth.models.security.AccessJwtToken;
import com.appl_maint_mngt.auth.models.security.IJwtToken;
import com.appl_maint_mngt.auth.models.security.Scopes;
import com.appl_maint_mngt.auth.models.security.UserContext;
import com.appl_maint_mngt.auth.security.constants.IAuthSecurityErrors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenFactory {

	private final JwtConfig config;
	
	@Autowired
	public JwtTokenFactory(JwtConfig config) {
		this.config = config;
	}
	
	public IJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getEmail())) 
            throw new IllegalArgumentException(IAuthSecurityErrors.CANNOT_CREATE_JWT_EMAIL);

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) 
            throw new IllegalArgumentException(IAuthSecurityErrors.USER_NO_PRIVILEGES);

        Claims claims = Jwts.claims().setSubject(userContext.getEmail());
        claims.put(IJwtConstants.SCOPES, userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        DateTime currentTime = new DateTime();

        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(config.getTokenIssuer())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(config.getTokenExpirationTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, config.getTokenSigningKey())
        .compact();

        return new AccessJwtToken(token, claims);
	}
	
	public IJwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getEmail())) 
            throw new IllegalArgumentException(IAuthSecurityErrors.CANNOT_CREATE_JWT_EMAIL);
        DateTime currentTime = new DateTime();
        Claims claims = Jwts.claims().setSubject(userContext.getEmail());
        claims.put(IJwtConstants.SCOPES, Arrays.asList(Scopes.REFRESH_TOKEN.authority()));

        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(config.getTokenIssuer())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(config.getTokenExpirationTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, config.getTokenSigningKey())
        .compact();
        return new AccessJwtToken(token, claims);
	}
}
