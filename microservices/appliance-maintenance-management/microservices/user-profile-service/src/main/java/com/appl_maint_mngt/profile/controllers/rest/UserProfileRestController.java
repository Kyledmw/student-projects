package com.appl_maint_mngt.profile.controllers.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;
import com.appl_maint_mngt.profile.models.IUserProfileReadable;
import com.appl_maint_mngt.profile.models.web.UserProfilePayload;
import com.appl_maint_mngt.profile.services.IUserProfileService;
import com.appl_maint_mngt.profile.validation.IUserProfileValidator;
import com.appl_maint_mngt.profile.web.constants.IProfileMessages;

@RestController
@RequestMapping("/")
public class UserProfileRestController implements IUserProfileRestApi {
	
	@Autowired
	private IUserProfileService service;
	
	@Autowired
	private IUserProfileValidator validator;

	@Override
	public ApiResponse<IUserProfileReadable> get(@PathVariable("id") Long id) {
		IUserProfileReadable profile = service.getByAccountId(id);
		return new ApiResponse<IUserProfileReadable>(ApiResponseStatus.SUCCESS, IProfileMessages.PROFILE_GET_SUCCESS, profile);
	}

	@Override
	public ApiResponse<IUserProfileReadable> create(@Valid @RequestBody UserProfilePayload payload) {
		
		if(!validator.isAccountIdUnique(payload.getAccountId())) {
			return new ApiResponse<IUserProfileReadable>(ApiResponseStatus.ERROR, IProfileMessages.RECORD_EXISTS, null);
		}
		IUserProfileReadable profile = service.save(payload);
		return new ApiResponse<IUserProfileReadable>(ApiResponseStatus.SUCCESS, IProfileMessages.PROFILE_CREATE_SUCCESS, profile);
	}

	@Override
	public ApiResponse<IUserProfileReadable> update(@Valid @RequestBody UserProfilePayload payload) {
		IUserProfileReadable profile = service.save(payload);
		return new ApiResponse<IUserProfileReadable>(ApiResponseStatus.SUCCESS, IProfileMessages.PROFILE_UPDATE_SUCCESS, profile);
	}

}
