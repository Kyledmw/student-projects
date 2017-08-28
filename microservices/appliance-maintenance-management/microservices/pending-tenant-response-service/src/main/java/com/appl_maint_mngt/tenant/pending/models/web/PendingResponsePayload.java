package com.appl_maint_mngt.tenant.pending.models.web;

import javax.validation.constraints.NotNull;

import com.appl_maint_mngt.tenant.pending.validation.ResponseType;

public class PendingResponsePayload implements IPendingResponsePayload {

	@NotNull(message="{pendresp.resptype.null}")
	@ResponseType
	private String responseType;

	@NotNull(message="{pendresp.mngid.null}")
	private Long managerId;

	@NotNull(message="{pendresp.tntid.null}")
	private Long tenantId;

	@NotNull(message="{pendresp.propid.null}")
	private Long propertyId;

	@Override
	public String getResponseType() {
		return responseType;
	}

	@Override
	public Long getManagerId() {
		return managerId;
	}

	@Override
	public Long getTenantId() {
		return tenantId;
	}

	@Override
	public Long getPropertyId() {
		return propertyId;
	}

	@Override
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	@Override
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	@Override
	public void setTenantid(Long tenantId) {
		this.tenantId = tenantId;
		
	}

	@Override
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

}
