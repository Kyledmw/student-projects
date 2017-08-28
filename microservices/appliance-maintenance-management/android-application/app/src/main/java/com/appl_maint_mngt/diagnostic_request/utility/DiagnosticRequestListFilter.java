package com.appl_maint_mngt.diagnostic_request.utility;

import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.utility.interfaces.IDiagnosticRequestListFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestListFilter implements IDiagnosticRequestListFilter {
    @Override
    public List<IDiagnosticRequestReadable> filterStatus(List<IDiagnosticRequestReadable> diagnosticRequests, ResponseStatus status) {
        List<IDiagnosticRequestReadable> diagRequest = new ArrayList<>();

        for(IDiagnosticRequestReadable diagReq: diagnosticRequests) {
            if(diagReq.getResponseStatus().equals(status)) {
                diagRequest.add(diagReq);
            }
        }
        return diagRequest;
    }
}
