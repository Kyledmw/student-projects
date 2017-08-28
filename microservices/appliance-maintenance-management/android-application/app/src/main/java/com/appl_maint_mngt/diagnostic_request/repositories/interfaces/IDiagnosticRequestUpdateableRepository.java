package com.appl_maint_mngt.diagnostic_request.repositories.interfaces;

import com.appl_maint_mngt.diagnostic_request.models.DiagnosticRequest;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestUpdateableRepository {

    void addItem(DiagnosticRequest request);

    void addItems(List<DiagnosticRequest> reqs);
}
