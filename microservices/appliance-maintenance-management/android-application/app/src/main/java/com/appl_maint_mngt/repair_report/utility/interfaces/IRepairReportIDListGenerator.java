package com.appl_maint_mngt.repair_report.utility.interfaces;

import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public interface IRepairReportIDListGenerator {

    List<Long> generate(List<IRepairReportReadable> list);
}
