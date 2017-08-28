package com.appl_maint_mngt.repair_report.models.interfaces;

import java.math.BigDecimal;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportReadable {

    Long getId();
    Long getEngineerId();

    String getTitle();
    Long getDiagnosticReportId();
    String getDescription();
    int getEstimatedDurationHours();

    boolean isOnSite();
    BigDecimal getCost();
}
