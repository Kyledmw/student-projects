package com.appl_maint_mgt.property_appliance.models;

import java.sql.Timestamp;

public class StatusHistory {

	private Long statusId;
	
	private Timestamp loggedTime;
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Timestamp getLoggedTime() {
		return loggedTime;
	}
	public void setLoggedTime(Timestamp loggedTime) {
		this.loggedTime = loggedTime;
	}
}
