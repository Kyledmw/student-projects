package com.appl_maint_mngt.diagnostic_report.models.interfaces;

import java.sql.Timestamp;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IDiagnosticReportWriteable {
    void setId(Long id);
    void setPropApplId(Long propApplId);
    void setTitle(String title);
    void setIssuedTime(Timestamp issuedTime);
    void setDescription(String description);
}
