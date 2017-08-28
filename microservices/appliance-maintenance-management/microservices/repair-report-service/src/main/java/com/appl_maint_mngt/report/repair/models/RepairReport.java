package com.appl_maint_mngt.report.repair.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="repair_reports")
public class RepairReport {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="engineer_id")
	@NotNull(message="{rprep.engineerid.null}")
	private Long engineerId;
	
	@Column(name="diagnostic_report_id")
	@NotNull(message="{rprep.diagrep.null}")
	private Long diagnosticReportId;
	
	@Column(name="description")
	@NotNull(message="{rprep.desc.null}")
	@Size(min=10, max=100, message="{rprep.desc.size}")
	private String description;

	@Column(name="estimated_duration_hours")
	@NotNull(message="{rprep.estdh.null}")
	@Min(value=1, message="{rprep.estdh.min}")
	@Max(value=12, message="{rprep.estdh.max}")
	private int estimatedDurationHours;

	@Column(name="on_site")
	@NotNull(message="{rprep.onsite.null}")
	private boolean onSite;

	@Column(name="cost")
	@NotNull(message="{rprep.onsite.cost}")
	private BigDecimal cost;
	
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(Long engineerId) {
		this.engineerId = engineerId;
	}

	public Long getDiagnosticReportId() {
		return diagnosticReportId;
	}

	public void setDiagnosticReportId(Long diagnosticReportId) {
		this.diagnosticReportId = diagnosticReportId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEstimatedDurationHours() {
		return estimatedDurationHours;
	}

	public void setEstimatedDurationHours(int estimatedDurationHours) {
		this.estimatedDurationHours = estimatedDurationHours;
	}

	public boolean isOnSite() {
		return onSite;
	}

	public void setOnSite(boolean onSite) {
		this.onSite = onSite;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
