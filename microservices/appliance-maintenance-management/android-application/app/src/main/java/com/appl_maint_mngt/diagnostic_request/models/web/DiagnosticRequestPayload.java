package com.appl_maint_mngt.diagnostic_request.models.web;

import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestPayload;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestPayload implements IDiagnosticRequestPayload {
    private Long diagnosticReportId;
    private Long maintenanceOrganisationId;

    @Override
    public Long getDiagnosticReportId() {
        return diagnosticReportId;
    }

    @Override
    public void setDiagnosticReportId(Long diagnosticReportId) {
        this.diagnosticReportId = diagnosticReportId;
    }

    @Override
    public Long getMaintenanceOrganisationId() {
        return maintenanceOrganisationId;
    }

    @Override
    public void setMaintenanceOrganisationId(Long maintenanceOrganisationId) {
        this.maintenanceOrganisationId = maintenanceOrganisationId;
    }
}
