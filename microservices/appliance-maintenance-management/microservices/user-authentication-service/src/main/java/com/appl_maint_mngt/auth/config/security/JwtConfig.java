package com.appl_maint_mngt.auth.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="auth.security.jwt")
public class JwtConfig {

	private Integer tokenExpirationTime;
	private String tokenIssuer;
	private String tokenSigningKey;
	private Integer refreshTokenExpTime;
	
	public Integer getTokenExpirationTime() {
		return tokenExpirationTime;
	}
	public void setTokenExpirationTime(Integer tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
	public String getTokenIssuer() {
		return tokenIssuer;
	}
	public void setTokenIssuer(String tokenIssuer) {
		this.tokenIssuer = tokenIssuer;
	}
	public String getTokenSigningKey() {
		return tokenSigningKey;
	}
	public void setTokenSigningKey(String tokenSigningKey) {
		this.tokenSigningKey = tokenSigningKey;
	}
	public Integer getRefreshTokenExpTime() {
		return refreshTokenExpTime;
	}
	public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
		this.refreshTokenExpTime = refreshTokenExpTime;
	}
	
	
}
