package com.appl_maint_mngt.diagnostic_request.models.web.interfaces;

/**
 * Created by Kyle on 10/04/2017.
 */

public interface IDiagnosticRequestPayload {
    Long getDiagnosticReportId();

    void setDiagnosticReportId(Long diagnosticReportId);

    Long getMaintenanceOrganisationId();

    void setMaintenanceOrganisationId(Long maintenanceOrganisationId);
}
