package com.appl_maint_mngt.auth.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.appl_maint_mngt.auth.models.IUserAuthReadable;

@Component
public class LoginValidator implements ILoginValidator {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public boolean validateCredentials(IUserAuthReadable user, String password) {
		return encoder.matches(password, user.getPassword());
	}

}
