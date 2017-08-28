package com.appl_maint_mngt.maintenance_organisation.utility.interfaces;

import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public interface IMaintenanceOrganisationIDListGenerator {

    List<Long> generateForDiagnosticRequests(List<IDiagnosticRequestReadable> diagReqs);
}
