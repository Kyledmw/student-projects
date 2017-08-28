package com.appl_maint_mngt.property.manager.controllers.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;
import com.appl_maint_mngt.property.manager.models.web.PropertyTransferPayload;
import com.appl_maint_mngt.property.manager.services.IPropertyManagerService;
import com.appl_maint_mngt.property.manager.web.constants.IMessageConstants;

@RestController
public class PropertyManagerRestController implements IPropertyManagerRestApi {
	
	@Autowired
	private IPropertyManagerService pmServ;

	@Override
	public ApiResponse<Boolean> transferProperty(@Valid @RequestBody PropertyTransferPayload payload) {
		pmServ.transfer(payload.getPropertyManagerId(), payload.getReceivingManagerId(), payload.getPropertyId());
		return new ApiResponse<Boolean>(ApiResponseStatus.SUCCESS, IMessageConstants.TRANSFER_SUCCESS_MSG, true);
	}

}
