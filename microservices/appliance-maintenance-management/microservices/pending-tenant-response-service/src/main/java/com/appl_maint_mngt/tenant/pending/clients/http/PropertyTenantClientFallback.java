package com.appl_maint_mngt.tenant.pending.clients.http;

import org.springframework.stereotype.Component;

import com.appl_maint_mngt.property.tenant.models.IPropertyTenant;

@Component
public class PropertyTenantClientFallback implements IPropertyTenantClient {

	@Override
	public IPropertyTenant update(Long id, IPropertyTenant tenant) {
		return null;
	}

	@Override
	public IPropertyTenant get(Long id) {
		return null;
	}

}
