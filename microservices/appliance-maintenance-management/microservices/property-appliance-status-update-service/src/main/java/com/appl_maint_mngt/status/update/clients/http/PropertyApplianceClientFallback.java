package com.appl_maint_mngt.status.update.clients.http;

import org.springframework.stereotype.Component;

import com.appl_maint_mgt.property_appliance.models.PropertyAppliance;

@Component
public class PropertyApplianceClientFallback implements IPropertyApplianceClient{

	@Override
	public PropertyAppliance get(Long id) {
		return null;
	}

	@Override
	public PropertyAppliance update(Long id, PropertyAppliance pAppl) {
		return null;
	}

}
