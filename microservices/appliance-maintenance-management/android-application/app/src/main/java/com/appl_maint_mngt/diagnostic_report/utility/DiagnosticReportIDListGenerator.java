package com.appl_maint_mngt.diagnostic_report.utility;

import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.utility.interfaces.IDiagnosticReportIDListGenerator;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticReportIDListGenerator implements IDiagnosticReportIDListGenerator {
    @Override
    public List<Long> generate(List<IDiagnosticReportReadable> diagnosticReportList) {
        List<Long> list = new ArrayList<>();

        for(IDiagnosticReportReadable diagRep: diagnosticReportList) {
            list.add(diagRep.getId());
        }

        return list;
    }

    @Override
    public List<Long> generateForRequests(List<IDiagnosticRequestReadable> diagnosticRequestList) {
        List<Long> list = new ArrayList<>();

        for(IDiagnosticRequestReadable diagReq: diagnosticRequestList) {
            list.add(diagReq.getDiagnosticReportId());
        }

        return list;
    }
}
