package com.appl_maint_mngt.property.tenant.models;

import java.util.List;

import com.appl_maint_mngt.property.tenant.models.constants.ResidenceStatus;

public interface IPropertyTenant {

	public Long getCurrentPropertyId();

	public void setCurrentPropertyId(Long currentPropertyId);

	public List<Long> getPreviousPropertyIds();

	public void setPreviousPropertyIds(List<Long> previousPropertyIds);

	public ResidenceStatus getResidenceStatus();

	public void setResidenceStatus(ResidenceStatus residenceStatus);
}
