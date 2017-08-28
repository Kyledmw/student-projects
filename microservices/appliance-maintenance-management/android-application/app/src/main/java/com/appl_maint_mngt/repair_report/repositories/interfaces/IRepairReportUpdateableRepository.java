package com.appl_maint_mngt.repair_report.repositories.interfaces;
import com.appl_maint_mngt.repair_report.models.RepairReport;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportUpdateableRepository {

    void addItems(List<RepairReport> repairReportsList);

    void addItem(RepairReport repairReport);
}
