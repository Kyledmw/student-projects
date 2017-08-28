package com.appl_maint_mngt.pending_repair_report.utility.interfaces;

import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 13/04/2017.
 */

public interface IPendingRepairReportRetriever {

    List<IPendingRepairReportReadable> retrievePending();

    List<IPendingRepairReportReadable> retrievePendingAndDeclined();
}
