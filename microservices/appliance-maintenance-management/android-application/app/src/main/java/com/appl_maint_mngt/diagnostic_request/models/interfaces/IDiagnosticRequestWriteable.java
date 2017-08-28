package com.appl_maint_mngt.diagnostic_request.models.interfaces;

import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestWriteable {
    void setId(Long id);
    void setDiagnosticReportId(Long diagnosticReportId);
    void setResponseStatus(ResponseStatus responseStatus);
    void setMaintenanceOrganisationId(Long maintenanceOrganisationId);
}
