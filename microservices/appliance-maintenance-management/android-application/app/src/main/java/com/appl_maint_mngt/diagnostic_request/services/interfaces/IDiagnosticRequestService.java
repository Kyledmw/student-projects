package com.appl_maint_mngt.diagnostic_request.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestPayload;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestUpdatePayload;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public interface IDiagnosticRequestService {
    void post(IDiagnosticRequestPayload payload, IErrorCallback errorCallback);

    void findByDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback);

    void findByDiagnosticReportIds(List<Long> diagRepIds, IErrorCallback errorCallback);

    void findByMaintenanceOrganisationId(Long maintOrgId, IErrorCallback errorCallback);

    void put(IDiagnosticRequestUpdatePayload payload, IErrorCallback errorCallback);

}
