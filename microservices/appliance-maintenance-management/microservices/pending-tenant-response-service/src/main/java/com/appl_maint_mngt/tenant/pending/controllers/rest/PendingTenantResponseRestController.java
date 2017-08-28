package com.appl_maint_mngt.tenant.pending.controllers.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;
import com.appl_maint_mngt.tenant.pending.models.web.PendingResponsePayload;
import com.appl_maint_mngt.tenant.pending.services.IPendingTenantResponseService;
import com.appl_maint_mngt.tenant.pending.web.constants.IResponseMessages;

@RestController
@RequestMapping("/")
public class PendingTenantResponseRestController implements IPendingTenantResponseRestApi {
	
	@Autowired
	private IPendingTenantResponseService responseServ;

	@Override
	public @ResponseBody ApiResponse<Boolean> respondToTenant(@Valid @RequestBody PendingResponsePayload payload) {
		
		boolean res = responseServ.postResponseToPropertyTenant(payload);
		if(res) {
			return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.TENANT_RESPONSE_SUCCESSFUL, true);
		} else {
			return new ApiResponse<>(ApiResponseStatus.ERROR, IResponseMessages.TENANT_RESPONSE_ERR, false);
		}
	}

}
