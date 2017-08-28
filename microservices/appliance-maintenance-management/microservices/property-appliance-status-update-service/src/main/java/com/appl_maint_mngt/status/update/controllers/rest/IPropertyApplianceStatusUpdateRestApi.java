package com.appl_maint_mngt.status.update.controllers.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.status.update.models.web.UpdatePayload;

public interface IPropertyApplianceStatusUpdateRestApi {

	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody ApiResponse<Boolean> update(@Valid @RequestBody UpdatePayload payload);
}
