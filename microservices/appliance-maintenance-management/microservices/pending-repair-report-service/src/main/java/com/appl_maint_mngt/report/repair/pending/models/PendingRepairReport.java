package com.appl_maint_mngt.report.repair.pending.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appl_maint_mngt.report.repair.models.IRepairReport;
import com.appl_maint_mngt.report.repair.pending.models.constants.ResponseStatus;

@Entity
@Table(name="pending_repair_reports")
public class PendingRepairReport implements IRepairReport {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column(name="diagnostic_report_id")
	private Long diagnosticReportId;

	@Column(name="diagnostic_request_id")
    private Long diagnosticRequestId;
	
	@Column(name="engineer_id")
    private Long engineerId;

	@Enumerated(EnumType.STRING)
	@Column(name="response_status")
    private ResponseStatus responseStatus;

	@Column(name="title")
    private String title;

	@Column(name="description")
    private String description;

	@Column(name="estimated_duration_hours")
    private int estimatedDurationHours;

	@Column(name="on_site")
    private boolean onSite;
    
	@Column(name="cost")
    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiagnosticRequestId() {
        return diagnosticRequestId;
    }

    public void setDiagnosticRequestId(Long diagnosticRequestId) {
        this.diagnosticRequestId = diagnosticRequestId;
    }

    public Long getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Long engineerId) {
        this.engineerId = engineerId;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

	public Long getDiagnosticReportId() {
		return diagnosticReportId;
	}

	public void setDiagnosticReportId(Long diagnosticReportId) {
		this.diagnosticReportId = diagnosticReportId;
	}

}
