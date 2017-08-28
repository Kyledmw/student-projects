package com.appl_maint_mngt.pending_repair_report.models.web.interfaces;

import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;

import java.math.BigDecimal;

/**
 * Created by Kyle on 11/04/2017.
 */

public interface IPendingRepairReportPayload {

    Long getDiagnosticRequestId();

    void setDiagnosticRequestId(Long diagnosticRequestId);

    Long getDiagnosticReportId();
    void setDiagnosticReportId(Long diagnosticReportId);

    Long getEngineerId();

    void setEngineerId(Long engineerId);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    int getEstimatedDurationHours();

    void setEstimatedDurationHours(int estimatedDurationHours);

    boolean isOnSite();

    void setOnSite(boolean onSite);

    BigDecimal getCost();

    void setCost(BigDecimal cost);
}
