package com.appl_maint_mngt.tenant.pending.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.property.tenant.models.IPropertyTenant;
import com.appl_maint_mngt.property.tenant.models.constants.ResidenceStatus;
import com.appl_maint_mngt.tenant.pending.clients.http.IPropertyTenantClient;
import com.appl_maint_mngt.tenant.pending.models.constants.ResponseTypes;
import com.appl_maint_mngt.tenant.pending.models.web.IPendingResponsePayload;

@Service
public class PendingTenantResponseService implements IPendingTenantResponseService {
	
	private static final Long NO_PROPERTY = new Long(-1);
	
	@Autowired
	private IPropertyTenantClient client;

	@Override
	public boolean postResponseToPropertyTenant(IPendingResponsePayload payload) {
		IPropertyTenant pload  = client.get(payload.getTenantId());
		if(pload == null) {
			return false;
		}
		
		if(payload.getResponseType().equals(ResponseTypes.ACCEPT.toString())) {
			pload.setCurrentPropertyId(NO_PROPERTY);
			pload.setResidenceStatus(ResidenceStatus.NO_RESIDENCE);
		} else {
			pload.setResidenceStatus(ResidenceStatus.OCCUPANT);
		}
		
		pload = client.update(payload.getTenantId(), pload);
		
		return pload != null;
	}

}
