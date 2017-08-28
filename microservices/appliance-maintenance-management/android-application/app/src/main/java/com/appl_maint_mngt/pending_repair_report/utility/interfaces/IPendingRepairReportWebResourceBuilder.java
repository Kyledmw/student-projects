package com.appl_maint_mngt.pending_repair_report.utility.interfaces;

/**
 * Created by Kyle on 17/04/2017.
 */

public interface IPendingRepairReportWebResourceBuilder {

    String buildAcceptResource(Long id);
    String buildDeclineResource(Long id);
}
