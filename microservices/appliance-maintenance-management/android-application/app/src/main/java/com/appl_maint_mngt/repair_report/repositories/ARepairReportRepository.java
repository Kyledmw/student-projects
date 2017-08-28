package com.appl_maint_mngt.repair_report.repositories;

import com.appl_maint_mngt.repair_report.repositories.interfaces.IRepairReportReadableRepository;
import com.appl_maint_mngt.repair_report.repositories.interfaces.IRepairReportUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 21/03/2017.
 */

public abstract class ARepairReportRepository extends Observable implements IRepairReportReadableRepository, IRepairReportUpdateableRepository {
}
