package com.appl_maint_mngt.auth.models.converters;

import com.appl_maint_mngt.auth.models.UserAuth;
import com.appl_maint_mngt.auth.models.web.IRegisterPayload;

public interface IRegisterPayloadConverter {

	UserAuth toUserAuth(IRegisterPayload regPload);
}
