package com.appl_maint_mngt.status.update.models.web;

import javax.validation.constraints.NotNull;

public class UpdatePayload implements IUpdatePayload {

	@NotNull(message="update.papplid.null")
	private Long propertyApplianceId;
	
	@NotNull(message="update.newstatusid.null")
	private Long newApplianceStatusId;

	@Override
	public Long getPropertyApplianceId() {
		return propertyApplianceId;
	}

	@Override
	public Long getNewApplianceStatusId() {
		return newApplianceStatusId;
	}

	@Override
	public void setPropertyApplianceId(Long id) {
		this.propertyApplianceId = id;
	}

	@Override
	public void setNewApplianceStatusId(Long id) {
		this.newApplianceStatusId = id;
	}

}
