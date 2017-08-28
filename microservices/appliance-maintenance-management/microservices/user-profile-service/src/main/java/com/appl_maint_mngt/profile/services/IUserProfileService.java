package com.appl_maint_mngt.profile.services;

import com.appl_maint_mngt.profile.models.IUserProfileReadable;
import com.appl_maint_mngt.profile.models.web.IUserProfilePayload;

public interface IUserProfileService {

	IUserProfileReadable getByAccountId(Long accountId);
	
	IUserProfileReadable save(IUserProfilePayload payload);	
}
