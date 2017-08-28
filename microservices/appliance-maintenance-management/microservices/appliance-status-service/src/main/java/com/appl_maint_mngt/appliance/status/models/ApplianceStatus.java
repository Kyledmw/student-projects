package com.appl_maint_mngt.appliance.status.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance.status.models.constants.StatusType;

@Entity
@Table(name="appliance_statuses")
public class ApplianceStatus {

    @Id
	@Column(name="id") 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
	@Column(name="status_type") 
	@Enumerated(EnumType.STRING)
	private StatusType type;
	
	@Column(name="message") 
	private String message;
	
	@Column(name="icon_url") 
	private String iconUrl;
	
	@Column(name="appliance_type") 
	@Enumerated(EnumType.STRING)
	private ApplianceType applianceType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public StatusType getType() {
		return type;
	}
	public void setType(StatusType type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public ApplianceType getApplianceType() {
		return applianceType;
	}
	public void setApplianceType(ApplianceType applianceType) {
		this.applianceType = applianceType;
	}
	
	
}
