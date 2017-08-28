package com.appl_maint_mngt.diagnostic_report.repositories;

import com.appl_maint_mngt.diagnostic_report.repositories.interfaces.IDiagnosticReportReadableRepository;
import com.appl_maint_mngt.diagnostic_report.repositories.interfaces.IDiagnosticReportUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 18/03/2017.
 */

public abstract class ADiagnosticReportRepository extends Observable implements IDiagnosticReportReadableRepository, IDiagnosticReportUpdateableRepository {
}
