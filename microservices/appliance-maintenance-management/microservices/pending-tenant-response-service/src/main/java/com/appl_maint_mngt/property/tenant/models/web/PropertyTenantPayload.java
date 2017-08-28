package com.appl_maint_mngt.property.tenant.models.web;

import java.util.List;

import com.appl_maint_mngt.property.tenant.models.IPropertyTenant;
import com.appl_maint_mngt.property.tenant.models.constants.ResidenceStatus;

public class PropertyTenantPayload implements IPropertyTenant {

	private Long currentPropertyId;

	private List<Long> previousPropertyIds;

	private ResidenceStatus residenceStatus;

	public Long getCurrentPropertyId() {
		return currentPropertyId;
	}

	public void setCurrentPropertyId(Long currentPropertyId) {
		this.currentPropertyId = currentPropertyId;
	}

	public List<Long> getPreviousPropertyIds() {
		return previousPropertyIds;
	}

	public void setPreviousPropertyIds(List<Long> previousPropertyIds) {
		this.previousPropertyIds = previousPropertyIds;
	}

	public ResidenceStatus getResidenceStatus() {
		return residenceStatus;
	}

	public void setResidenceStatus(ResidenceStatus residenceStatus) {
		this.residenceStatus = residenceStatus;
	}

}
