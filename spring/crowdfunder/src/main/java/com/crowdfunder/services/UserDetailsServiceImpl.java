package com.crowdfunder.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdfunder.models.Role;
import com.crowdfunder.models.User;
import com.crowdfunder.repositories.interfaces.IUserRepository;

/**
 ********************************************************************
 * Implementation of the SpringSecurity provided interface UserDetailsService
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private IUserRepository _userRepo;

    @Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = _userRepo.findBy_userName(username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(Role role: user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}

}
