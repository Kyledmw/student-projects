package com.appl_maint_mngt.repair_report.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.repair_report.controllers.interfaces.IRepairReportController;
import com.appl_maint_mngt.repair_report.services.interfaces.IRepairReportService;

import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public class RepairReportController implements IRepairReportController {

    private IRepairReportService repairReportService;

    public RepairReportController() {
        repairReportService = IntegrationController.getInstance().getServiceFactory().createRepairReportService();
    }


    @Override
    public void getForDiagnosticId(Long diagnosticId, IErrorCallback errorCallback) {
        repairReportService.findByDiagnosticReportId(diagnosticId, errorCallback);
    }

    @Override
    public void getForDiagnosticIds(List<Long> diagnosticId, IErrorCallback errorCallback) {
        if(diagnosticId == null || diagnosticId.isEmpty()) return;
        repairReportService.findByDiagnosticReportIdsIn(diagnosticId, errorCallback);
    }

    @Override
    public void getForEngineer(Long engineerId, IErrorCallback errorCallback) {
        repairReportService.findByEngineerId(engineerId, errorCallback);
    }
}
