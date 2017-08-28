package com.appl_maint_mngt.pending_repair_report.models.interfaces;

import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;

import java.math.BigDecimal;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportWriteable {
    void setId(Long id);
    void setDiagnosticRequestId(Long diagnosticRequestId);
    void setDiagnosticReportId(Long diagnosticReportId);
    void setEngineerId(Long engineerId);

    void setResponseStatus(ResponseStatus responseStatus);

    void setTitle(String title);
    void setDescription(String description);
    void setEstimatedDurationHours(int estimatedDurationHours);
    void setOnSite(boolean onSite);
    void setCost(BigDecimal cost);
}
