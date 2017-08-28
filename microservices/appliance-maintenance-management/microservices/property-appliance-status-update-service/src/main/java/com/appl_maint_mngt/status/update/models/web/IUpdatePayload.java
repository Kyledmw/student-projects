package com.appl_maint_mngt.status.update.models.web;

public interface IUpdatePayload {
	
	Long getPropertyApplianceId();
	Long getNewApplianceStatusId();

	void setPropertyApplianceId(Long id);
	
	void setNewApplianceStatusId(Long id);
}
