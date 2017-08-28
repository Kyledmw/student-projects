package com.appl_maint_mngt.property.manager.controllers.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.property.manager.models.web.PropertyTransferPayload;

public interface IPropertyManagerRestApi {

	@RequestMapping(value="/transfer", method=RequestMethod.POST)
	@ResponseBody ApiResponse<Boolean> transferProperty(@Valid @RequestBody PropertyTransferPayload payload);
}
