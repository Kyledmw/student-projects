package com.appl_maint_mngt.pending_repair_report.repositories.interfaces;

import com.appl_maint_mngt.pending_repair_report.models.PendingRepairReport;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IPendingRepairReportUpdateableRepository {

    void addItem(PendingRepairReport pendingReport);

    void addItems(List<PendingRepairReport> pendingReports);
}
