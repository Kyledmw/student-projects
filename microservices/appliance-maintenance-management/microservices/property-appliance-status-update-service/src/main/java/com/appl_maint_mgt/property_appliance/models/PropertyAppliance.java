package com.appl_maint_mgt.property_appliance.models;

import java.util.List;

public class PropertyAppliance { 
	private String applianceId;

	private Long propertyId;

	private Long statusId;
	
	private String name;
	
	private int age;
	
	private List<StatusHistory> statusHistory;
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
