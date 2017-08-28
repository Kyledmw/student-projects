package com.appl_maint_mngt.diagnostic_report.models.web;

import com.appl_maint_mngt.diagnostic_report.models.web.interfaces.IDiagnosticReportForm;

import java.sql.Timestamp;

/**
 * Created by Kyle on 09/04/2017.
 */

public class DiagnosticReportForm implements IDiagnosticReportForm {

    private Long propApplId;
    private String title;
    private Timestamp issuedTime;
    private String description;

    public DiagnosticReportForm(){}

    public Long getPropApplId() {
        return propApplId;
    }

    public void setPropApplId(Long propertyApplianceId) {
        this.propApplId = propertyApplianceId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
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
}
