package com.crowdfunder.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crowdfunder.models.User;
import com.crowdfunder.repositories.interfaces.IRoleRepository;
import com.crowdfunder.repositories.interfaces.IUserRepository;
import com.crowdfunder.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository _userRepo;
	
	@Autowired
	private IRoleRepository _roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder _pwEncoder;

	@Override
	public void save(User user) {
		user.setPassword(_pwEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(_roleRepo.findAll()));
		_userRepo.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return _userRepo.findBy_userName(username);
	}
	
	@Override
	public User findByEmail(String email) {
		return _userRepo.findBy_email(email);
	}

}
