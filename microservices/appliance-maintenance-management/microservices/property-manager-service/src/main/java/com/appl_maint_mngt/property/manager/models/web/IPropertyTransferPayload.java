package com.appl_maint_mngt.property.manager.models.web;

public interface IPropertyTransferPayload {

	Long getPropertyManagerId();
	
	Long getReceivingManagerId();
	
	Long getPropertyId();
	
	void setPropertyManagerId(Long id);
	
	void setReceivingmanagerId(Long id);
	
	void setPropertyId(Long id);
}
