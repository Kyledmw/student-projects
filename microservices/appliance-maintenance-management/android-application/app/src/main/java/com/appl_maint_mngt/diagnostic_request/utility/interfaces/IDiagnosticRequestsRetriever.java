package com.appl_maint_mngt.diagnostic_request.utility.interfaces;

import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public interface IDiagnosticRequestsRetriever {

    List<IDiagnosticRequestReadable> getPendingAndResponded();

    List<IDiagnosticRequestReadable> getPending();
}
