package com.appl_maint_mngt.pending_repair_report.utility;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.pending_repair_report.constants.web.IPendingRepairReportWebResources;
import com.appl_maint_mngt.pending_repair_report.utility.interfaces.IPendingRepairReportWebResourceBuilder;

/**
 * Created by Kyle on 17/04/2017.
 */

public class PendingRepairReportWebResourceBuilder implements IPendingRepairReportWebResourceBuilder {

    @Override
    public String buildAcceptResource(Long id) {
        StringBuilder builder = new StringBuilder();
        builder.append(IPendingRepairReportWebResources.BASE_URL);
        builder.append(ICommonConstants.FORWARD_SLASH);
        builder.append(id);
        builder.append(IPendingRepairReportWebResources.ACCEPT_PATH);
        return builder.toString();
    }

    @Override
    public String buildDeclineResource(Long id) {
        StringBuilder builder = new StringBuilder();
        builder.append(IPendingRepairReportWebResources.BASE_URL);
        builder.append(ICommonConstants.FORWARD_SLASH);
        builder.append(id);
        builder.append(IPendingRepairReportWebResources.DECLINE_PATH);
        return builder.toString();
    }
}
