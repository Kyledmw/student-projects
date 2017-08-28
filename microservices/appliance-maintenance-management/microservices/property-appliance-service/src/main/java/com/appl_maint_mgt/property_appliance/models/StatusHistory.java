package com.appl_maint_mgt.property_appliance.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status_history")
public class StatusHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="status_id")
	private Long statusId;
	
	@Column(name="logged_time")
	private Timestamp loggedTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
