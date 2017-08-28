package com.appl_maint_mngt.pending_repair_report.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.pending_repair_report.models.web.interfaces.IPendingRepairReportPayload;

import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public interface IPendingRepairReportService {

    void accept(Long id, IErrorCallback errorCallback);

    void decline(Long id, IErrorCallback errorCallback);

    void findByDiagnosticRequestId(Long diagnosticRequestId, IErrorCallback errorCallback);

    void findByDiagnosticRequestIdIn(List<Long> ids, IErrorCallback errorCallback);

    void create(IPendingRepairReportPayload payload, IErrorCallback errorCallback);

    void findByEngineerId(Long engineerId, IErrorCallback errorCallback);
}
