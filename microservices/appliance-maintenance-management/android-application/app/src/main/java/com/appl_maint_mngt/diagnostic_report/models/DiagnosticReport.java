package com.appl_maint_mngt.diagnostic_report.models;

import java.sql.Timestamp;

/**
 * Created by Kyle on 18/03/2017.
 */

public class DiagnosticReport extends ADiagnosticReport {

    private Long id;
    private Long propApplId;
    private String title;
    private Timestamp issuedTime;
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getPropApplId() {
        return propApplId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setPropApplId(Long propApplId) {
        this.propApplId = propApplId;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Timestamp getIssuedTime() {
        return issuedTime;
    }

    @Override
    public void setIssuedTime(Timestamp issuedTime) {
        this.issuedTime = issuedTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
