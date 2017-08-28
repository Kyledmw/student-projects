package com.appl_maint_mngt.pending_repair_report.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.pending_repair_report.controllers.interfaces.IPendingRepairReportController;
import com.appl_maint_mngt.pending_repair_report.models.PendingRepairReport;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.models.web.interfaces.IPendingRepairReportPayload;
import com.appl_maint_mngt.pending_repair_report.services.interfaces.IPendingRepairReportService;

import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public class PendingRepairReportController implements IPendingRepairReportController {

    private IPendingRepairReportService pendingRepairReportService;

    public PendingRepairReportController() {
        pendingRepairReportService = IntegrationController.getInstance().getServiceFactory().createPendingRepairReportService();
    }

    @Override
    public void getForDiagnosticRequest(IDiagnosticRequestReadable request, IErrorCallback callback) {
        pendingRepairReportService.findByDiagnosticRequestId(request.getId(), callback);
    }

    @Override
    public void acceptPendingRepairReport(IPendingRepairReportReadable pendingRepReport, IErrorCallback callback) {
        pendingRepairReportService.accept(pendingRepReport.getId(), callback);
    }

    @Override
    public void declinePendingRepairReport(IPendingRepairReportReadable pendingRepReport, IErrorCallback callback) {
        pendingRepairReportService.decline(pendingRepReport.getId(), callback);
    }

    @Override
    public void createPendingRepairReport(IPendingRepairReportPayload payload, IErrorCallback errorCallback) {
        pendingRepairReportService.create(payload, errorCallback);
    }

    @Override
    public void getForEngineer(Long id, IErrorCallback errorCallback) {
        if(id == null) return;
        pendingRepairReportService.findByEngineerId(id, errorCallback);
    }

    @Override
    public void getForDiagnosticRequests(List<Long> diagnosticRequestIds, IErrorCallback errorCallback) {
        if(diagnosticRequestIds == null || diagnosticRequestIds.isEmpty()) return;
        pendingRepairReportService.findByDiagnosticRequestIdIn(diagnosticRequestIds, errorCallback);
    }
}
