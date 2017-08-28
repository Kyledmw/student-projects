package com.appl_maint_mngt.tenant.pending.controllers.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.tenant.pending.models.web.PendingResponsePayload;

public interface IPendingTenantResponseRestApi {

	@RequestMapping(value="/respond", method=RequestMethod.POST)
	@ResponseBody ApiResponse<Boolean> respondToTenant(@Valid @RequestBody PendingResponsePayload payload);
}
