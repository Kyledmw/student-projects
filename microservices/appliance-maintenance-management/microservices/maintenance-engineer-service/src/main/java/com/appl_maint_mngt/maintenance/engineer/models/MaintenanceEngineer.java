package com.appl_maint_mngt.maintenance.engineer.models;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="maintenance_engineers")
public class MaintenanceEngineer {
	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message="meng.accountid.null")
	private Long accountId;
	
	@Column(name="current_organisation_id")
	@NotNull(message="meng.corgid.null")
	private Long currentOrganisationId;
	
	@ElementCollection
	@CollectionTable(name = "previous_organisation_ids", joinColumns = @JoinColumn(name = "engineer_id"))
	@Column(name="organisation_id")
	private List<Long> previousOrganisationIds;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCurrentOrganisationId() {
		return currentOrganisationId;
	}

	public void setCurrentOrganisationId(Long currentOrganisationId) {
		this.currentOrganisationId = currentOrganisationId;
	}

	public List<Long> getPreviousOrganisationIds() {
		return previousOrganisationIds;
	}

	public void setPreviousOrganisationIds(List<Long> previousOrganisationIds) {
		this.previousOrganisationIds = previousOrganisationIds;
	}
}
