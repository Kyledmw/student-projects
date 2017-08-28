package com.appl_maint_mngt.repair_report.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public interface IRepairReportController {

    void getForDiagnosticId(Long diagnosticId, IErrorCallback errorCallback);

    void getForDiagnosticIds(List<Long> diagnosticId, IErrorCallback errorCallback);

    void getForEngineer(Long engineerId, IErrorCallback errorCallback);

}
