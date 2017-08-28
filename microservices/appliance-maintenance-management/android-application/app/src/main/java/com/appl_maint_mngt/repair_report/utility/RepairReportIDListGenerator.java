package com.appl_maint_mngt.repair_report.utility;

import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.utility.interfaces.IRepairReportIDListGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public class RepairReportIDListGenerator implements IRepairReportIDListGenerator {
    @Override
    public List<Long> generate(List<IRepairReportReadable> list) {
        List<Long> ids = new ArrayList<>();
        for(IRepairReportReadable repairReport: list) {
            ids.add(repairReport.getId());
        }
        return ids;
    }
}
