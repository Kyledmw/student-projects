package com.appl_maint_mngt.auth.services;

import com.appl_maint_mngt.auth.models.UserAuth;
import com.appl_maint_mngt.auth.models.web.IRegisterPayload;

public interface IUserAuthService {

	UserAuth getByEmail(String email);
	
	UserAuth create(IRegisterPayload regPload);
}
