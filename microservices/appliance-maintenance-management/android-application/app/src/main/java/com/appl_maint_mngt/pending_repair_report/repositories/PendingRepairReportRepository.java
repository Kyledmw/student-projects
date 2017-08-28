package com.appl_maint_mngt.pending_repair_report.repositories;

import android.util.LongSparseArray;

import com.appl_maint_mngt.pending_repair_report.models.PendingRepairReport;
import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.repositories.constants.IPendingRepairReportRepositoryObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 21/03/2017.
 */
public class PendingRepairReportRepository extends APendingRepairReportRepository {

    private LongSparseArray<PendingRepairReport> repairReports;

    public PendingRepairReportRepository() {
        repairReports = new LongSparseArray<>();
    }

    @Override
    public IPendingRepairReportReadable getForDiagnosticRequestId(Long diagnosticRequestId) {
        for(int i = 0; i<repairReports.size(); i++) {
            if(repairReports.valueAt(i).getDiagnosticRequestId().equals(diagnosticRequestId)) {
                return repairReports.valueAt(i);
            }
        }
        return null;
    }

    @Override
    public List<IPendingRepairReportReadable> getAll() {
        List<IPendingRepairReportReadable> list = new ArrayList<>();
        for(int i = 0; i<repairReports.size(); i++) {
            list.add(repairReports.valueAt(i));
        }
        return list;
    }

    @Override
    public List<IPendingRepairReportReadable> getAllForStatus(ResponseStatus status) {
        List<IPendingRepairReportReadable> list = new ArrayList<>();
        for(int i = 0; i<repairReports.size(); i++) {
            if(repairReports.valueAt(i).getResponseStatus().equals(status)) {
                list.add(repairReports.valueAt(i));
            }
        }
        return list;
    }

    @Override
    public IPendingRepairReportReadable getForId(Long id) {
        return repairReports.get(id);
    }

    @Override
    public void addItem(PendingRepairReport pendingReport) {
        repairReports.put(pendingReport.getId(), pendingReport);
        updateObservers(IPendingRepairReportRepositoryObserverUpdateTypes.ADD_ITEM);
    }

    @Override
    public void addItems(List<PendingRepairReport> pendingReports) {
        for(PendingRepairReport report: pendingReports) {
            repairReports.put(report.getId(), report);
        }
        updateObservers(IPendingRepairReportRepositoryObserverUpdateTypes.MODEL_UPDATE);
    }


    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
