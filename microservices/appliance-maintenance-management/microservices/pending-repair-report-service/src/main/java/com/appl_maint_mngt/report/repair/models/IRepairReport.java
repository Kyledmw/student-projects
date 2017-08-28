package com.appl_maint_mngt.report.repair.models;

import java.math.BigDecimal;

public interface IRepairReport {
	
	public Long getEngineerId();

	public void setEngineerId(Long engineerId);

	public Long getDiagnosticReportId();

	public void setDiagnosticReportId(Long diagnosticReportId);

	public String getDescription();

	public void setDescription(String description);

	public int getEstimatedDurationHours();

	public void setEstimatedDurationHours(int estimatedDurationHours);

	public boolean isOnSite();

	public void setOnSite(boolean onSite);

	public BigDecimal getCost();

	public void setCost(BigDecimal cost);
}
