package com.appl_maint_mngt.report.diagnostic.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="diagnostic_reports")
public class DiagnosticReport {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="prop_appl_id")
	@NotNull(message="{diagrep.prop_appl.null}")
	private Long propApplId;

	@Column(name="issued_time")
	@NotNull(message="{diagrep.issued_time.null}")
	private Timestamp issuedTime;

	@Column(name="description")
	@NotNull(message="{diagrep.desc.null}")
	@Size(min=10, max=100, message="{diagrep.desc.size}")
	private String description;
	
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPropApplId() {
		return propApplId;
	}

	public void setPropApplId(Long propApplId) {
		this.propApplId = propApplId;
	}

	public Timestamp getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Timestamp issuedTime) {
		this.issuedTime = issuedTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
