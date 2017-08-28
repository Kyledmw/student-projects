package com.appl_maint_mngt.auth.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.appl_maint_mngt.auth.models.security.JwtAuthenticationToken;
import com.appl_maint_mngt.auth.models.security.RawAccessJwtToken;
import com.appl_maint_mngt.auth.web.constants.IAuthWebConstants;

public class JwtAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

	private final AuthenticationFailureHandler failureHandler;
	
	private final ITokenExtractor tokenExtractor;
	
	public JwtAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, ITokenExtractor extractor, AuthenticationFailureHandler failureHandler) {
		super(requiresAuthenticationRequestMatcher);
		this.failureHandler = failureHandler;
		this.tokenExtractor = extractor;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String tokenPayload = request.getHeader(IAuthWebConstants.JWT_TOKEN_HEADER_PARAM);
		RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
		return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) 
			throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) 
			throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request,  response, failed);
	}
}
