package com.appl_maint_mngt.repair_report.models;

import java.math.BigDecimal;

/**
 * Created by Kyle on 11/04/2017.
 */

public class RepairReport extends ARepairReport {

    private Long id;

    private Long engineerId;

    private Long diagnosticReportId;

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
    public Long getEngineerId() {
        return engineerId;
    }

    @Override
    public void setEngineerId(Long engineerId) {
        this.engineerId = engineerId;
    }

    @Override
    public Long getDiagnosticReportId() {
        return diagnosticReportId;
    }

    @Override
    public void setDiagnosticReportId(Long diagnosticReportId) {
        this.diagnosticReportId = diagnosticReportId;
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
}
