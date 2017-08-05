package com.crowdfunder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdfunder.models.User;
import com.crowdfunder.services.interfaces.ISecurityService;
import com.crowdfunder.services.interfaces.IUserSecurityService;
import com.crowdfunder.services.interfaces.IUserService;

@Service
public class UserSecurityService implements IUserSecurityService {
	
	@Autowired
	private ISecurityService _secService;
	
	@Autowired
	private IUserService _userService;

	@Override
	public User getLoggedInUser() {
		return _userService.findByUsername(_secService.findLoggedInUsername());
	}

}
