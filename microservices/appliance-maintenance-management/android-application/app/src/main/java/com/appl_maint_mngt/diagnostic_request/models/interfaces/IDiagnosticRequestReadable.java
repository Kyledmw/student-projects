package com.appl_maint_mngt.diagnostic_request.models.interfaces;

import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestReadable {
    Long getId();
    Long getDiagnosticReportId();

    ResponseStatus getResponseStatus();

    Long getMaintenanceOrganisationId();
}
