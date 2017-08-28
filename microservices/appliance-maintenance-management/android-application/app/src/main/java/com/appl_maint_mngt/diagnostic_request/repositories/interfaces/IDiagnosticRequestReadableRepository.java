package com.appl_maint_mngt.diagnostic_request.repositories.interfaces;

import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */

public interface IDiagnosticRequestReadableRepository {

    IDiagnosticRequestReadable getForId(Long id);

    List<IDiagnosticRequestReadable> getAll();

    List<IDiagnosticRequestReadable> getAllPending();

    List<IDiagnosticRequestReadable> getForDiagnosticReportId(Long diagRepId);
}
