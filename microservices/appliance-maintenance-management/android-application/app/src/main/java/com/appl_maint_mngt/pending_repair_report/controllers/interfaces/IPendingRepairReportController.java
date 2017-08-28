package com.appl_maint_mngt.pending_repair_report.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.models.web.interfaces.IPendingRepairReportPayload;

import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public interface IPendingRepairReportController {

    void getForDiagnosticRequest(IDiagnosticRequestReadable request, IErrorCallback callback);

    void acceptPendingRepairReport(IPendingRepairReportReadable pendingRepReport, IErrorCallback callback);

    void declinePendingRepairReport(IPendingRepairReportReadable pendingRepReport, IErrorCallback callback);

    void createPendingRepairReport(IPendingRepairReportPayload payload, IErrorCallback errorCallback);

    void getForEngineer(Long id, IErrorCallback errorCallback);

    void getForDiagnosticRequests(List<Long> diagnosticRequestIds, IErrorCallback errorCallback);
}
