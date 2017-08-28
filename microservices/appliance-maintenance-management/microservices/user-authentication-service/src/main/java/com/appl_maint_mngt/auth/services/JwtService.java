package com.appl_maint_mngt.auth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.auth.config.security.JwtConfig;
import com.appl_maint_mngt.auth.models.IUserAuthReadable;
import com.appl_maint_mngt.auth.models.security.IJwtToken;
import com.appl_maint_mngt.auth.models.security.RawAccessJwtToken;
import com.appl_maint_mngt.auth.models.security.RefreshToken;
import com.appl_maint_mngt.auth.models.security.UserContext;
import com.appl_maint_mngt.auth.security.exceptions.InvalidJwtTokenException;
import com.appl_maint_mngt.auth.security.jwt.ITokenExtractor;
import com.appl_maint_mngt.auth.security.jwt.ITokenVerifier;
import com.appl_maint_mngt.auth.security.jwt.JwtTokenFactory;
import com.appl_maint_mngt.auth.web.constants.IAuthErrors;

@Service
public class JwtService implements IJwtService {
	
	@Autowired
	private JwtConfig jwtConfig;
	
	@Autowired
	private JwtTokenFactory tokenFactory;
	
	@Autowired
    private ITokenExtractor tokenExtractor;
	
	@Autowired
	private ITokenVerifier tokenVerifier;

	@Override
	public IJwtToken getTokenForUser(IUserAuthReadable user) throws InsufficientAuthenticationException {

        if (user.getRoles() == null) throw new InsufficientAuthenticationException(IAuthErrors.USER_NO_ROLES);

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
        
        UserContext userContext = UserContext.create(user.getEmail(), authorities);
		return tokenFactory.createAccessJwtToken(userContext);
	}

	@Override
	public RefreshToken getRefreshToken(String payload) throws InvalidJwtTokenException {
		String tokenPayload = tokenExtractor.extract(payload);
		
		RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
		RefreshToken refToken = RefreshToken.create(rawToken, jwtConfig.getTokenSigningKey());
		
		if(refToken == null) {
			throw new InvalidJwtTokenException();
		}

		if(!tokenVerifier.verify(refToken.getJti())) {
			throw new InvalidJwtTokenException();
		}
		
		return refToken;
	}

}
