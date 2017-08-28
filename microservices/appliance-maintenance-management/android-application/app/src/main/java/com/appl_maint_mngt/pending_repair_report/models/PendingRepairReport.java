package com.appl_maint_mngt.pending_repair_report.models;

import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by Kyle on 11/04/2017.
 */

public class PendingRepairReport extends APendingRepairReport {

    private Long id;
    private Long diagnosticReportId;
    private Long diagnosticRequestId;
    private Long engineerId;

    @SerializedName("responseStatus")
    private ResponseStatus responseStatus;

    private String title;
    private String description;
    private int estimatedDurationHours;
    private boolean onSite;
    private BigDecimal cost;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getDiagnosticRequestId() {
        return diagnosticRequestId;
    }

    @Override
    public void setDiagnosticRequestId(Long diagnosticRequestId) {
        this.diagnosticRequestId = diagnosticRequestId;
    }

    @Override
    public Long getEngineerId() {
        return engineerId;
    }

    @Override
    public void setEngineerId(Long engineerId) {
        this.engineerId = engineerId;
    }

    @Override
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    @Override
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getEstimatedDurationHours() {
        return estimatedDurationHours;
    }

    @Override
    public void setEstimatedDurationHours(int estimatedDurationHours) {
        this.estimatedDurationHours = estimatedDurationHours;
    }

    @Override
    public boolean isOnSite() {
        return onSite;
    }

    @Override
    public void setOnSite(boolean onSite) {
        this.onSite = onSite;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public Long getDiagnosticReportId() {
        return diagnosticReportId;
    }

    @Override
    public void setDiagnosticReportId(Long diagnosticReportId) {
        this.diagnosticReportId = diagnosticReportId;
    }
}
