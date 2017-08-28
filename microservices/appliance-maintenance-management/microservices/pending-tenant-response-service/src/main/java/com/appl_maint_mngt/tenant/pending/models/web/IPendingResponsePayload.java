package com.appl_maint_mngt.tenant.pending.models.web;

public interface IPendingResponsePayload {

	String getResponseType();
	
	Long getManagerId();
	
	Long getTenantId();
	
	Long getPropertyId();
	
	void setResponseType(String responseType);
	
	void setManagerId(Long managerId);
	
	void setTenantid(Long tenantId);
	
	void setPropertyId(Long propertyId);
}
