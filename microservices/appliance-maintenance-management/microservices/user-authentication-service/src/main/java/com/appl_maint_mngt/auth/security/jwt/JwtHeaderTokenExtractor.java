package com.appl_maint_mngt.auth.security.jwt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import com.appl_maint_mngt.auth.security.constants.IAuthSecurityErrors;
import com.appl_maint_mngt.auth.web.constants.IAuthWebConstants;

@Component
public class JwtHeaderTokenExtractor implements ITokenExtractor {

	@Override
	public String extract(String payload) {
        if (StringUtils.isBlank(payload)) {
            throw new AuthenticationServiceException(IAuthSecurityErrors.AUTH_HEADER_BLANK_ERR);
        }
        if (payload.length() < IAuthWebConstants.HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException(IAuthSecurityErrors.INV_AUTH_HEADER_SZ);
        }
        return payload.substring(IAuthWebConstants.HEADER_PREFIX.length(), payload.length());
	}

}
