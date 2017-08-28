package com.appl_maint_mngt.diagnostic_request.utility;

import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.utility.interfaces.IDiagnosticRequestIDListGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 13/04/2017.
 */

public class DiagnosticRequestIDListGenerator implements IDiagnosticRequestIDListGenerator {

    @Override
    public List<Long> generate(List<IDiagnosticRequestReadable> list) {
        List<Long> ids = new ArrayList<>();
        for(IDiagnosticRequestReadable diagReq : list) {
            ids.add(diagReq.getId());
        }
        return ids;
    }
}
