package com.appl_maint_mngt.pending_repair_report.models.interfaces;

import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;

import java.math.BigDecimal;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportReadable {


    Long getId();
    Long getDiagnosticRequestId();
    Long getDiagnosticReportId();
    Long getEngineerId();

    ResponseStatus getResponseStatus();

    String getTitle();
    String getDescription();
    int getEstimatedDurationHours();
    boolean isOnSite();
    BigDecimal getCost();
}
