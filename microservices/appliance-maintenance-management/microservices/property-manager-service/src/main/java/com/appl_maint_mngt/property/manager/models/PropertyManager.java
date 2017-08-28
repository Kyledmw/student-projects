package com.appl_maint_mngt.property.manager.models;

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
@Table(name="property_managers")
public class PropertyManager {

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message="pmng.accountid.null")
	private Long accountId;
	
	@ElementCollection
	@CollectionTable(name = "current_property_ids", joinColumns = @JoinColumn(name = "manager_id"))
	@Column(name="property_id")
	private List<Long> currentPropertyIds;
	
	@ElementCollection
	@CollectionTable(name = "previous_property_ids", joinColumns = @JoinColumn(name = "manager_id"))
	@Column(name="property_id")
	private List<Long> previousPropertyIds;
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public List<Long> getCurrentPropertyIds() {
		return currentPropertyIds;
	}
	public void setCurrentPropertyIds(List<Long> currentPropertyIds) {
		this.currentPropertyIds = currentPropertyIds;
	}
	public List<Long> getPreviousPropertyIds() {
		return previousPropertyIds;
	}
	public void setPreviousPropertyIds(List<Long> previousPropertyIds) {
		this.previousPropertyIds = previousPropertyIds;
	}
	
	
}
