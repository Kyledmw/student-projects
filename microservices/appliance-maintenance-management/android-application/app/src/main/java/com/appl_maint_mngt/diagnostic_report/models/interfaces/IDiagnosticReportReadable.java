package com.appl_maint_mngt.diagnostic_report.models.interfaces;

import java.sql.Timestamp;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IDiagnosticReportReadable {

    Long getId();
    Long getPropApplId();
    String getTitle();
    Timestamp getIssuedTime();
    String getDescription();

}
