package com.appl_maint_mngt.auth.validation;

import com.appl_maint_mngt.auth.models.IUserAuthReadable;

public interface ILoginValidator {

	boolean validateCredentials(IUserAuthReadable user, String password);
}
