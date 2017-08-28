package com.appl_maint_mngt.property.tenant.models;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.appl_maint_mngt.property.tenant.models.constants.ResidenceStatus;

@Entity
@Table(name = "property_tenants")
public class PropertyTenant implements IPropertyTenant{

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message="{proptnt.accountid.null}")
	private Long accountId;

	@Column(name = "current_property_id")
	private Long currentPropertyId = new Long(-1);

	@ElementCollection
	@CollectionTable(name = "previous_property_ids", joinColumns = @JoinColumn(name = "tenant_id"))
	@Column(name="property_id")
	private List<Long> previousPropertyIds;

	@Enumerated(EnumType.STRING)
	@Column(name = "residence_status")
	private ResidenceStatus residenceStatus = ResidenceStatus.NO_RESIDENCE;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

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
