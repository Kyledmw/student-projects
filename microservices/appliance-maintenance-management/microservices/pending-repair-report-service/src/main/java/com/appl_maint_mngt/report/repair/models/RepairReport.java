package com.appl_maint_mngt.report.repair.models;

import java.math.BigDecimal;

public class RepairReport implements IRepairReport {

	private Long id;
	
	private Long engineerId;
	
	private Long diagnosticReportId;
	
	private String description;

	private int estimatedDurationHours;

	private boolean onSite;

	private BigDecimal cost;

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
	
	
}
