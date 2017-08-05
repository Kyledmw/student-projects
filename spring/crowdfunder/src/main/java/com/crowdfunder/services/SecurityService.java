package com.crowdfunder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.crowdfunder.constants.ISharedConstants;
import com.crowdfunder.services.interfaces.ISecurityService;

@Service
public class SecurityService implements ISecurityService {
	
	@Autowired
	private AuthenticationManager _authManager;
	
	@Autowired
	private UserDetailsService _userDetServ;

	@Override
	public String findLoggedInUsername() {
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			return ISharedConstants.ANON_USER;
		}
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@Override
	public void autoLogin(String username, String password) {
        UserDetails userDetails = _userDetServ.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        _authManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
		
	}

}
