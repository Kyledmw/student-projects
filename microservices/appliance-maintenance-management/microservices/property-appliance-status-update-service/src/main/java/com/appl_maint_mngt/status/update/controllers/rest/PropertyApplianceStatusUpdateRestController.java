package com.appl_maint_mngt.status.update.controllers.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;
import com.appl_maint_mngt.status.update.models.web.UpdatePayload;
import com.appl_maint_mngt.status.update.services.IPropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.status.update.services.web.constants.IResponseMessages;

@RestController
@RequestMapping(value="/")
public class PropertyApplianceStatusUpdateRestController implements IPropertyApplianceStatusUpdateRestApi {
	
	@Autowired
	private IPropertyApplianceStatusUpdateService applStatUpdateServ;

	@Override
	public @ResponseBody ApiResponse<Boolean> update(@Valid @RequestBody UpdatePayload payload) {
		boolean res = applStatUpdateServ.updatePropertyApplianceStatus(payload);
		if(res) {
			return new ApiResponse<Boolean>(ApiResponseStatus.SUCCESS, IResponseMessages.UPDATE_SUCCESS_MSG, res);
		}
		return new ApiResponse<Boolean>(ApiResponseStatus.ERROR, IResponseMessages.UPDATE_ERR_MSG, res);
	}

}
