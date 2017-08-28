package com.appl_maint_mngt.auth.controllers.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.auth.models.IUserAuthReadable;
import com.appl_maint_mngt.auth.models.security.IJwtToken;
import com.appl_maint_mngt.auth.models.security.JwtAuthenticationToken;
import com.appl_maint_mngt.auth.models.security.RefreshToken;
import com.appl_maint_mngt.auth.models.security.UserContext;
import com.appl_maint_mngt.auth.models.web.AuthDetails;
import com.appl_maint_mngt.auth.models.web.IAuthDetails;
import com.appl_maint_mngt.auth.models.web.LoginPayload;
import com.appl_maint_mngt.auth.models.web.RegisterPayload;
import com.appl_maint_mngt.auth.security.exceptions.InvalidJwtTokenException;
import com.appl_maint_mngt.auth.services.IJwtService;
import com.appl_maint_mngt.auth.services.IUserAuthService;
import com.appl_maint_mngt.auth.validation.ILoginValidator;
import com.appl_maint_mngt.auth.web.constants.IAuthErrors;
import com.appl_maint_mngt.auth.web.constants.IAuthMessages;
import com.appl_maint_mngt.auth.web.constants.IAuthWebConstants;
import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;

@RestController
@RequestMapping("/")
public class AuthRestController implements IAuthRestApi {
	
	@Autowired
	private IUserAuthService userAuthService;
	
	@Autowired
	private IJwtService jwtService;
	
	@Autowired
	private ILoginValidator loginValidator;

	@Override
	public ApiResponse<IJwtToken> login(@Valid @RequestBody LoginPayload payload) {
		IUserAuthReadable user = userAuthService.getByEmail(payload.getEmail());
		if(!loginValidator.validateCredentials(user, payload.getPassword())) throw new BadCredentialsException(IAuthErrors.AUTH_INVALID);
		return new ApiResponse<IJwtToken>(ApiResponseStatus.SUCCESS, IAuthMessages.AUTH_SUCCESS_MSG, jwtService.getTokenForUser(user));
	}

	@Override
	public ApiResponse<IJwtToken> register(@Valid @RequestBody RegisterPayload payload) {
		IUserAuthReadable user = userAuthService.create(payload);
		return new ApiResponse<IJwtToken>(ApiResponseStatus.SUCCESS, IAuthMessages.AUTH_SUCCESS_MSG, jwtService.getTokenForUser(user));
	}

	@Override
	public ApiResponse<IJwtToken> refresh(HttpServletRequest request, HttpServletResponse response) throws InvalidJwtTokenException {
		RefreshToken refToken = jwtService.getRefreshToken(request.getHeader(IAuthWebConstants.JWT_TOKEN_HEADER_PARAM));
		String subject = refToken.getSubject();
		IUserAuthReadable user = userAuthService.getByEmail(subject);
		return new ApiResponse<IJwtToken>(ApiResponseStatus.SUCCESS, IAuthMessages.AUTH_SUCCESS_MSG, jwtService.getTokenForUser(user));
	}

	@Override
	public ApiResponse<IAuthDetails> getDetails(JwtAuthenticationToken token) {
		UserContext context = (UserContext) token.getPrincipal();
		IUserAuthReadable user = userAuthService.getByEmail(context.getEmail());
		IAuthDetails details = new AuthDetails(user);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IAuthMessages.AUTH_SUCCESS_MSG, details);
	}

}
