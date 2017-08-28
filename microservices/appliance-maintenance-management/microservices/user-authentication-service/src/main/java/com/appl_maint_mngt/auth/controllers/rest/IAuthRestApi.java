package com.appl_maint_mngt.auth.controllers.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.auth.models.security.IJwtToken;
import com.appl_maint_mngt.auth.models.security.JwtAuthenticationToken;
import com.appl_maint_mngt.auth.models.web.IAuthDetails;
import com.appl_maint_mngt.auth.models.web.LoginPayload;
import com.appl_maint_mngt.auth.models.web.RegisterPayload;
import com.appl_maint_mngt.auth.security.exceptions.InvalidJwtTokenException;
import com.appl_maint_mngt.auth.web.constants.IAuthRestEntryPoints;
import com.appl_maint_mngt.common.models.web.ApiResponse;

public interface IAuthRestApi {

	@RequestMapping(value = IAuthRestEntryPoints.REST_LOGIN_ENTRY_POINT, method = RequestMethod.POST)
	@ResponseBody ApiResponse<IJwtToken> login(@Valid @RequestBody LoginPayload payload);
	
	@RequestMapping(value = IAuthRestEntryPoints.REST_REG_ENTRY_POINT, method = RequestMethod.POST)
	@ResponseBody ApiResponse<IJwtToken> register(@Valid @RequestBody RegisterPayload payload);
	
	@RequestMapping(value = IAuthRestEntryPoints.REST_REFRESH_ENTRY_POINT, method=RequestMethod.GET)
	@ResponseBody ApiResponse<IJwtToken> refresh(HttpServletRequest request, HttpServletResponse response) throws InvalidJwtTokenException ;
	
	@RequestMapping(value = IAuthRestEntryPoints.REST_DETAILS_ENTRY_POINT, method=RequestMethod.GET)
	@ResponseBody ApiResponse<IAuthDetails> getDetails(JwtAuthenticationToken token);
}
