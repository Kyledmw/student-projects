package com.appl_maint_mngt.diagnostic_report.repositories.interfaces;


import com.appl_maint_mngt.diagnostic_report.models.DiagnosticReport;

import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */
public interface IDiagnosticReportUpdateableRepository {

    void addItem(DiagnosticReport report);

    void addItems(List<DiagnosticReport> reports);
}
