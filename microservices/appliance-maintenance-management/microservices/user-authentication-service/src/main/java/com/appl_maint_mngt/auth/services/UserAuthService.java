package com.appl_maint_mngt.auth.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.auth.models.UserAuth;
import com.appl_maint_mngt.auth.models.converters.IRegisterPayloadConverter;
import com.appl_maint_mngt.auth.models.roles.Role;
import com.appl_maint_mngt.auth.models.roles.UserRole;
import com.appl_maint_mngt.auth.models.web.IRegisterPayload;
import com.appl_maint_mngt.auth.repositories.IUserAuthRepository;

@Service
public class UserAuthService implements IUserAuthService {
	
	@Autowired
	private IRegisterPayloadConverter regPloadConv;

	@Autowired
	private IUserAuthRepository userRepo;
	
	@Override
	public UserAuth getByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public UserAuth create(IRegisterPayload regPload) {
		UserAuth userAuth = regPloadConv.toUserAuth(regPload);
    	List<UserRole> roles = new ArrayList<>();

    	userRepo.save(userAuth);
    	
    	UserRole role = new UserRole();
    	
    	role.setUserAuthId(userAuth.getId());
    	role.setRole(Role.MEMBER);
    	userAuth.setRoles(roles);
    	
    	userRepo.save(userAuth);
		return userAuth;
	}

}
