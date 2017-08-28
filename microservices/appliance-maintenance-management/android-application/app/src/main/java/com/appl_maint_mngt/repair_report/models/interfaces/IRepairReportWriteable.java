package com.appl_maint_mngt.repair_report.models.interfaces;

import java.math.BigDecimal;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportWriteable {
    void setId(Long id);

    void setEngineerId(Long engineerId);

    void setDiagnosticReportId(Long diagnosticReportId);

    void setTitle(String title);
    void setDescription(String description);
    void setEstimatedDurationHours(int estimatedDurationHours);

    void setOnSite(boolean onSite);

    void setCost(BigDecimal cost);
}
