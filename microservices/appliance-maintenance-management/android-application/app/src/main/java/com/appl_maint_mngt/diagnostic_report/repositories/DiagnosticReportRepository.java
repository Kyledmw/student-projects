package com.appl_maint_mngt.diagnostic_report.repositories;

import android.util.LongSparseArray;

import com.appl_maint_mngt.diagnostic_report.models.ADiagnosticReport;
import com.appl_maint_mngt.diagnostic_report.models.DiagnosticReport;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.repositories.constants.IDiagnosticReportObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public class DiagnosticReportRepository extends ADiagnosticReportRepository {

    private LongSparseArray<DiagnosticReport> diagnosticReports;
    private ADiagnosticReport mostRecent;

    public DiagnosticReportRepository() {
        this.diagnosticReports = new LongSparseArray<>();
    }

    @Override
    public void addItem(DiagnosticReport report) {
        diagnosticReports.put(report.getId(), report);
        mostRecent = report;
        updateObservers(IDiagnosticReportObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public void addItems(List<DiagnosticReport> reports) {
        for(DiagnosticReport rep: reports) {
            diagnosticReports.put(rep.getId(), rep);
        }
        updateObservers(IDiagnosticReportObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IDiagnosticReportReadable get(Long id) {
        return diagnosticReports.get(id);
    }

    @Override
    public IDiagnosticReportReadable getMostRecent() {
        return mostRecent;
    }

    @Override
    public List<IDiagnosticReportReadable> getAll() {
        List<IDiagnosticReportReadable> list = new ArrayList<>();
        for(int i=0; i< diagnosticReports.size(); i++) {
            list.add(diagnosticReports.valueAt(i));
        }
        return list;
    }

    @Override
    public List<IDiagnosticReportReadable> getAllForPropertyAppliance(Long propApplId) {
        List<IDiagnosticReportReadable> list = new ArrayList<>();
        for(int i=0; i< diagnosticReports.size(); i++) {
            if(diagnosticReports.valueAt(i).getPropApplId().equals(propApplId)) {
                list.add(diagnosticReports.valueAt(i));
            }
        }
        return list;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
