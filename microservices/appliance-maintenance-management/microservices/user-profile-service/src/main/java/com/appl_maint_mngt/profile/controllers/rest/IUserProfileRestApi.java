package com.appl_maint_mngt.profile.controllers.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.profile.models.IUserProfileReadable;
import com.appl_maint_mngt.profile.models.web.UserProfilePayload;
import com.appl_maint_mngt.profile.web.constants.IUserProfileRestEntrypoints;

public interface IUserProfileRestApi {

	@RequestMapping(value=IUserProfileRestEntrypoints.REST_ID_ENTRY_POINT, method=RequestMethod.GET)
	@ResponseBody ApiResponse<IUserProfileReadable> get(@PathVariable("id") Long id);

	@RequestMapping(value=IUserProfileRestEntrypoints.REST_CREATE_ENTRY_POINT, method=RequestMethod.POST)
	@ResponseBody ApiResponse<IUserProfileReadable> create(@Valid @RequestBody UserProfilePayload payload);

	@RequestMapping(value=IUserProfileRestEntrypoints.REST_UPDATE_ENTRY_POINT, method=RequestMethod.POST)
	@ResponseBody ApiResponse<IUserProfileReadable> update(@Valid @RequestBody UserProfilePayload payload);
}
