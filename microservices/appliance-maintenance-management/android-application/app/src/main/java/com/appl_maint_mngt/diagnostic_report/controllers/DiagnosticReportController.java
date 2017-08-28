package com.appl_maint_mngt.diagnostic_report.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_report.controllers.interfaces.IDiagnosticReportController;
import com.appl_maint_mngt.diagnostic_report.models.web.interfaces.IDiagnosticReportForm;
import com.appl_maint_mngt.diagnostic_report.services.interfaces.IDiagnosticReportService;

import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public class DiagnosticReportController implements IDiagnosticReportController {

    private IDiagnosticReportService diagnosticReportService;

    public DiagnosticReportController() {
        diagnosticReportService = IntegrationController.getInstance().getServiceFactory().createDiagnosticReportService();
    }

    @Override
    public void generateDiagnosticReport(IDiagnosticReportForm form, IErrorCallback errorCallback) {
        diagnosticReportService.post(form, errorCallback);
    }

    @Override
    public void getForPropertyAppliance(Long propertyApplianceId, IErrorCallback errorCallback) {
        if(propertyApplianceId == null) return;
        diagnosticReportService.getForPropertyApplianceId(propertyApplianceId, errorCallback);
    }

    @Override
    public void getForPropertyAppliances(List<Long> propertyApplianceIds, IErrorCallback errorCallback) {
        if(propertyApplianceIds == null || propertyApplianceIds.isEmpty()) return;
        diagnosticReportService.findByPropertyApplianceIdsIn(propertyApplianceIds, errorCallback);
    }

    @Override
    public void getForDiagnosticReportIds(List<Long> ids, IErrorCallback errorCallback) {
        if(ids == null || ids.isEmpty()) return;
        diagnosticReportService.findByIdsIn(ids, errorCallback);
    }

    @Override
    public void getForDiagnosticReportId(Long id, IErrorCallback errorCallback) {
        diagnosticReportService.get(id, errorCallback);
    }
}
