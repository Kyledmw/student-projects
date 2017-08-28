package com.appl_maint_mngt.pending_maintenance_scheduling.utility.interfaces;

/**
 * Created by Kyle on 17/04/2017.
 */

public interface IPendingMaintenanceSchedulingWebResourceBuilder {
    String buildAcceptResource(Long id);
    String buildDeclineResource(Long id);
    String buildPendingReportResource(Long id);
}
