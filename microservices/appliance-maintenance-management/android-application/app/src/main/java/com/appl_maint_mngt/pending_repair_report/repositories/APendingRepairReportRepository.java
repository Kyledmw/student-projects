package com.appl_maint_mngt.pending_repair_report.repositories;

import com.appl_maint_mngt.pending_repair_report.repositories.interfaces.IPendingRepairReportReadableRepository;
import com.appl_maint_mngt.pending_repair_report.repositories.interfaces.IPendingRepairReportUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 11/04/2017.
 */

public abstract class APendingRepairReportRepository extends Observable implements IPendingRepairReportReadableRepository, IPendingRepairReportUpdateableRepository {
}
