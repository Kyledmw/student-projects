package com.appl_maint_mngt.diagnostic_request.controllers.interfaces;

import android.util.LongSparseArray;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestUpdatePayload;
import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public interface IDiagnosticRequestController {
    void createRequests(Long diagReqId, List<IMaintenanceOrganisationReadable> maintOrgs, IErrorCallback errorCallback);

    void getForDiagnosticReportId(Long diagRepId, IErrorCallback errorCallback);

    void getForDiagnosticReportIds(List<Long> diagRepIds, IErrorCallback errorCallback);

    void getForMaintenanceOrgId(Long maintOrgId, IErrorCallback errorCallback);

    void updateDiagnosticRequest(IDiagnosticRequestUpdatePayload payload, IErrorCallback errorCallback);
 }
