package com.appl_maint_mngt.maintenance_organisation.utility;

import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.maintenance_organisation.utility.interfaces.IMaintenanceOrganisationIDListGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class MaintenanceOrganisationIDListGenerator implements IMaintenanceOrganisationIDListGenerator {
    @Override
    public List<Long> generateForDiagnosticRequests(List<IDiagnosticRequestReadable> diagReqs) {
        List<Long> list = new ArrayList<>();

        for(IDiagnosticRequestReadable diagnosticRequest: diagReqs) {
            list.add(diagnosticRequest.getMaintenanceOrganisationId());
        }

        return list;
    }
}
