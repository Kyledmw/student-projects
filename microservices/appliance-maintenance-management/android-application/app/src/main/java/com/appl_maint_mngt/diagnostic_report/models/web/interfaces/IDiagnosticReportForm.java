package com.appl_maint_mngt.diagnostic_report.models.web.interfaces;

import java.sql.Timestamp;

/**
 * Created by Kyle on 09/04/2017.
 */

public interface IDiagnosticReportForm {
    Long getPropApplId();

    void setPropApplId(Long propertyApplianceId);

    String getTitle();

    void setTitle(String title);

    Timestamp getIssuedTime();

    void setIssuedTime(Timestamp issuedTime);

    String getDescription();

    void setDescription(String description);

}
