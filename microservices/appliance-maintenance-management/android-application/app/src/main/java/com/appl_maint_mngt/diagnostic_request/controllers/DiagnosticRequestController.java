package com.appl_maint_mngt.diagnostic_request.controllers;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_request.controllers.interfaces.IDiagnosticRequestController;
import com.appl_maint_mngt.diagnostic_request.models.web.DiagnosticRequestPayload;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestUpdatePayload;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;
import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestController implements IDiagnosticRequestController {

    private IDiagnosticRequestService diagnosticRequestService;

    public DiagnosticRequestController() {
        diagnosticRequestService = IntegrationController.getInstance().getServiceFactory().createDiagnosticRequestService();
    }

    @Override
    public void createRequests(Long diagReqId, List<IMaintenanceOrganisationReadable> maintOrgs, IErrorCallback errorCallback) {
        for(IMaintenanceOrganisationReadable maintOrg: maintOrgs) {
            DiagnosticRequestPayload payload = new DiagnosticRequestPayload();
            payload.setDiagnosticReportId(diagReqId);
            payload.setMaintenanceOrganisationId(maintOrg.getId());
            diagnosticRequestService.post(payload, errorCallback);
        }
    }

    @Override
    public void getForDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback) {
        diagnosticRequestService.findByDiagnosticReportId(diagRepId, errorCallback);
    }

    @Override
    public void getForDiagnosticReportIds(List<Long> diagRepIds, IErrorCallback errorCallback) {
        if(diagRepIds == null || diagRepIds.isEmpty()) return;
        diagnosticRequestService.findByDiagnosticReportIds(diagRepIds, errorCallback);
    }

    @Override
    public void getForMaintenanceOrgId(Long maintOrgId, IErrorCallback errorCallback) {
        diagnosticRequestService.findByMaintenanceOrganisationId(maintOrgId, errorCallback);
    }

    @Override
    public void updateDiagnosticRequest(IDiagnosticRequestUpdatePayload payload, IErrorCallback errorCallback) {
        diagnosticRequestService.put(payload, errorCallback);
    }
}
