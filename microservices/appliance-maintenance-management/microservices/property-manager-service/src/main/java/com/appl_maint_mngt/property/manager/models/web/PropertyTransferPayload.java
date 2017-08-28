package com.appl_maint_mngt.property.manager.models.web;

import javax.validation.constraints.NotNull;

import com.appl_maint_mngt.property.manager.validation.ManagerExists;

public class PropertyTransferPayload implements IPropertyTransferPayload {

	@NotNull(message="transfer.pmng.null")
	@ManagerExists
	private Long propertyManagerId;
	
	@NotNull(message="transfer.rec.null")
	@ManagerExists
	private Long receivingManagerId;
	
	@NotNull(message="transfer.prop.null")
	private Long propertyId;

	@Override
	public Long getPropertyManagerId() {
		return propertyManagerId;
	}

	@Override
	public Long getReceivingManagerId() {
		return receivingManagerId;
	}

	@Override
	public Long getPropertyId() {
		return propertyId;
	}

	@Override
	public void setPropertyManagerId(Long id) {
		this.propertyManagerId = id;
	}

	@Override
	public void setReceivingmanagerId(Long id) {
		this.receivingManagerId = id;
	}

	@Override
	public void setPropertyId(Long id) {
		this.propertyId = id;
	}

}
