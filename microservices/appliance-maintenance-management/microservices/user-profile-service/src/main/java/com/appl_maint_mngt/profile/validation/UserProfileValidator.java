package com.appl_maint_mngt.profile.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appl_maint_mngt.profile.models.IUserProfileReadable;
import com.appl_maint_mngt.profile.services.IUserProfileService;

@Component
public class UserProfileValidator implements IUserProfileValidator {
	
	@Autowired
	private IUserProfileService serv;

	@Override
	public boolean isAccountIdUnique(Long accountId) {
		IUserProfileReadable profile = serv.getByAccountId(accountId);
		return profile == null;
	}

}
