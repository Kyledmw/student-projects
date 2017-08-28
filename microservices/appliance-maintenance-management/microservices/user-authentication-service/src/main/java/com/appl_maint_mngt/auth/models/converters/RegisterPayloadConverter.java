package com.appl_maint_mngt.auth.models.converters;

import org.springframework.stereotype.Component;

import com.appl_maint_mngt.auth.models.UserAuth;
import com.appl_maint_mngt.auth.models.UserType;
import com.appl_maint_mngt.auth.models.web.IRegisterPayload;

@Component
public class RegisterPayloadConverter implements IRegisterPayloadConverter {

	@Override
	public UserAuth toUserAuth(IRegisterPayload regPload) {
		UserAuth user = new UserAuth();
		user.setEmail(regPload.getEmail());
		user.setPassword(regPload.getPassword());
		user.setUserType(UserType.valueOf(regPload.getUserType()));
		return user;
	}

}
