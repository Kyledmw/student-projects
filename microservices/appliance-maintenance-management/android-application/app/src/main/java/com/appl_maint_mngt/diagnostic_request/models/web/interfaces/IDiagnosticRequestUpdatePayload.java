package com.appl_maint_mngt.diagnostic_request.models.web.interfaces;

import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;

/**
 * Created by Kyle on 10/04/2017.
 */

public interface IDiagnosticRequestUpdatePayload {
    Long getDiagnosticReportId();

    void setDiagnosticReportId(Long diagnosticReportId);

    Long getMaintenanceOrganisationId();

    void setMaintenanceOrganisationId(Long maintenanceOrganisationId);

    Long getId();

    void setId(Long id);

    ResponseStatus getResponseStatus();

    void setResponseStatus(ResponseStatus responseStatus);
}
