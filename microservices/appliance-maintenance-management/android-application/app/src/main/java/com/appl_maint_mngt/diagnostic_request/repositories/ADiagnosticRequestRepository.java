package com.appl_maint_mngt.diagnostic_request.repositories;

import com.appl_maint_mngt.diagnostic_request.repositories.interfaces.IDiagnosticRequestReadableRepository;
import com.appl_maint_mngt.diagnostic_request.repositories.interfaces.IDiagnosticRequestUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 21/03/2017.
 */

public abstract class ADiagnosticRequestRepository extends Observable implements IDiagnosticRequestReadableRepository, IDiagnosticRequestUpdateableRepository {
}
