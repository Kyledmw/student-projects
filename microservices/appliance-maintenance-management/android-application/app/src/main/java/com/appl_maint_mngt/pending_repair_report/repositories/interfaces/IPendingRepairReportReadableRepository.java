package com.appl_maint_mngt.pending_repair_report.repositories.interfaces;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */
public interface IPendingRepairReportReadableRepository {

    IPendingRepairReportReadable getForDiagnosticRequestId(Long diagnosticRequestId);

    List<IPendingRepairReportReadable> getAll();

    List<IPendingRepairReportReadable> getAllForStatus(ResponseStatus status);

    IPendingRepairReportReadable getForId(Long id);

}
