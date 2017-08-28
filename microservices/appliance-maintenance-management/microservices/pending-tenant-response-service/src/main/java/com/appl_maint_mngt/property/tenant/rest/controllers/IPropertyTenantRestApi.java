package com.appl_maint_mngt.property.tenant.rest.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.property.tenant.models.IPropertyTenant;

public interface IPropertyTenantRestApi {

	@RequestMapping(value="/{id}", method=RequestMethod.PUT) 
	@ResponseBody IPropertyTenant update(@PathVariable("id") Long id, @RequestBody IPropertyTenant tenant);
	
	@RequestMapping(value="{/id}", method=RequestMethod.GET)
	@ResponseBody IPropertyTenant get(@PathVariable("id") Long id);
}
