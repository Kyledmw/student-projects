package com.appl_maint_mgt.property_appliance.controllers.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mgt.property_appliance.models.PropertyAppliance;

public interface IPropertyApplianceRestApi {

	@RequestMapping(value="/data/{id}", method=RequestMethod.GET)
	@ResponseBody PropertyAppliance get(@PathVariable("id") Long id);
	
	@RequestMapping(value="/data/{id}", method=RequestMethod.PUT)
	@ResponseBody PropertyAppliance update(@PathVariable("id") Long id, @RequestBody PropertyAppliance pAppl);
}
