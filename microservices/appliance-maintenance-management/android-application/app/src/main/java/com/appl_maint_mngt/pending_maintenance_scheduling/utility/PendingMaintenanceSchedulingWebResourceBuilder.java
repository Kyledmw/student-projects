package com.appl_maint_mngt.pending_maintenance_scheduling.utility;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.pending_maintenance_scheduling.constants.web.IPendingMaintenanceSchedulingWebResources;
import com.appl_maint_mngt.pending_maintenance_scheduling.utility.interfaces.IPendingMaintenanceSchedulingWebResourceBuilder;

/**
 * Created by Kyle on 17/04/2017.
 */

public class PendingMaintenanceSchedulingWebResourceBuilder implements IPendingMaintenanceSchedulingWebResourceBuilder {

    @Override
    public String buildAcceptResource(Long id) {
        StringBuilder builder = new StringBuilder();
        builder.append(IPendingMaintenanceSchedulingWebResources.BASE_URL);
        builder.append(ICommonConstants.FORWARD_SLASH);
        builder.append(id);
        builder.append(IPendingMaintenanceSchedulingWebResources.ACCEPT_PATH);
        return builder.toString();
    }

    @Override
    public String buildDeclineResource(Long id) {
        StringBuilder builder = new StringBuilder();
        builder.append(IPendingMaintenanceSchedulingWebResources.BASE_URL);
        builder.append(ICommonConstants.FORWARD_SLASH);
        builder.append(id);
        builder.append(IPendingMaintenanceSchedulingWebResources.DECLINE_PATH);
        return builder.toString();
    }

    @Override
    public String buildPendingReportResource(Long id) {
        StringBuilder builder = new StringBuilder();
        builder.append(IPendingMaintenanceSchedulingWebResources.BASE_URL);
        builder.append(IPendingMaintenanceSchedulingWebResources.REPORT_PATH);
        builder.append(id);
        builder.append(IPendingMaintenanceSchedulingWebResources.PENDING_PATH);
        return builder.toString();
    }
}
