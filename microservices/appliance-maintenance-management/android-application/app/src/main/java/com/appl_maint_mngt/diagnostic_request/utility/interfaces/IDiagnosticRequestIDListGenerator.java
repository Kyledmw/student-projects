package com.appl_maint_mngt.diagnostic_request.utility.interfaces;

import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

import java.util.List;

/**
 * Created by Kyle on 13/04/2017.
 */

public interface IDiagnosticRequestIDListGenerator {

    List<Long> generate(List<IDiagnosticRequestReadable> list);
}
