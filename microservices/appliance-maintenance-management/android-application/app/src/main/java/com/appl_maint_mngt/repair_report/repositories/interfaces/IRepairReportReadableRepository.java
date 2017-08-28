package com.appl_maint_mngt.repair_report.repositories.interfaces;

import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IRepairReportReadableRepository {

    IRepairReportReadable getForId(Long id);

    IRepairReportReadable getForDiagnosticReportId(Long id);

    List<IRepairReportReadable> getAll();
}
