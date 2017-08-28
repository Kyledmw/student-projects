package com.appl_maint_mgt.property_appliance.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="property_appliances")
public class PropertyAppliance { 

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="appliance_id")
	@NotNull(message="{prop_appl.appl.null}")
	private String applianceId;

	@Column(name="property_id")
	@NotNull(message="{prop_appl.prop.null}")
	private Long propertyId;

	@Column(name="status_id")
	@NotNull(message="{prop_appl.status.null}")
	private Long statusId;

	@NotNull(message="{prop_appl.name.null}")
	@Size(min=2, max=20, message="{prop_appl.name.size}")
	private String name;

	@NotNull(message="{prop_appl.age.null}")
	@Min(value=1, message="{prop_appl.age.min}")
	@Max(value=100, message="{prop_appl.age.max}")
	private int age;
	
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="pappl_stat_hist", 
          joinColumns=@JoinColumn(name="pappl_id"),
          inverseJoinColumns=@JoinColumn(name="stat_hist_id"))
	private List<StatusHistory> statusHistory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplianceId() {
		return applianceId;
	}

	public void setApplianceId(String applianceId) {
		this.applianceId = applianceId;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<StatusHistory> getStatusHistory() {
		return statusHistory;
	}

	public void setStatusHistory(List<StatusHistory> statusHistory) {
		this.statusHistory = statusHistory;
	}
	
	
}
