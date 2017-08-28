package com.appl_maint_mngt.status.notification.models;

public class StatusNotification {

	private Long propertyApplianceId;
	private Long previousApplianceStatusId;
	private Long newApplianceStatusId;
	
	public Long getPropertyApplianceId() {
		return propertyApplianceId;
	}
	public void setPropertyApplianceId(Long propertyApplianceId) {
		this.propertyApplianceId = propertyApplianceId;
	}
	public Long getPreviousApplianceStatusId() {
		return previousApplianceStatusId;
	}
	public void setPreviousApplianceStatusId(Long previousApplianceStatusId) {
		this.previousApplianceStatusId = previousApplianceStatusId;
	}
	public Long getNewApplianceStatusId() {
		return newApplianceStatusId;
	}
	public void setNewApplianceStatusId(Long newApplianceStatusId) {
		this.newApplianceStatusId = newApplianceStatusId;
	}
	
	
}
