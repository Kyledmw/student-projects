package com.appl_maint_mngt.repair_report.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public interface IRepairReportService {

    void findByDiagnosticReportId(Long id, IErrorCallback errorCallback);

    void findByDiagnosticReportIdsIn(List<Long> ids, IErrorCallback errorCallback);

    void findByEngineerId(Long engineerId, IErrorCallback errorCallback);

}
