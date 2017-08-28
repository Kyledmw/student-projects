package com.appl_maint_mngt.diagnostic_report.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.diagnostic_report.models.DiagnosticReport;
import com.appl_maint_mngt.diagnostic_report.models.web.interfaces.IDiagnosticReportForm;
import com.appl_maint_mngt.diagnostic_report.repositories.interfaces.IDiagnosticReportUpdateableRepository;
import com.appl_maint_mngt.diagnostic_report.services.interfaces.IDiagnosticReportService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public class DummyDiagnosticReportService implements IDiagnosticReportService {
    @Override
    public void post(IDiagnosticReportForm diagRep, IErrorCallback callback) {
        IDiagnosticReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticReportRepository();

        DiagnosticReport report = new DiagnosticReport();
        report.setId((long) 1);
        report.setTitle(diagRep.getTitle());
        report.setDescription(diagRep.getDescription());
        report.setIssuedTime(diagRep.getIssuedTime());
        report.setPropApplId(diagRep.getPropApplId());
        repository.addItem(report);
    }

    @Override
    public void getForPropertyApplianceId(Long propertyApplianceId, IErrorCallback errorCallback) {
        IDiagnosticReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticReportRepository();
        List<DiagnosticReport> reports = new ArrayList<>();

        DiagnosticReport report1 = new DiagnosticReport();
        report1.setPropApplId(propertyApplianceId);
        report1.setIssuedTime(new Timestamp(System.currentTimeMillis()));
        report1.setTitle("Title");
        report1.setDescription("Description");
        report1.setId((long) 1);
        reports.add(report1);

        repository.addItems(reports);
    }

    @Override
    public void findByPropertyApplianceIdsIn(List<Long> propertyApplianceIds, IErrorCallback errorCallback) {
        IDiagnosticReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticReportRepository();

        List<DiagnosticReport> reports = new ArrayList<>();
        long counter = 1;
        for(Long id: propertyApplianceIds) {
            DiagnosticReport report = new DiagnosticReport();
            report.setId(counter);
            report.setIssuedTime(new Timestamp(System.currentTimeMillis()));
            report.setTitle("Title");
            report.setDescription("Description");
            report.setPropApplId(id);
            reports.add(report);
            counter++;
        }
        repository.addItems(reports);
    }

    @Override
    public void findByIdsIn(List<Long> ids, IErrorCallback errorCallback) {
        IDiagnosticReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticReportRepository();

        List<DiagnosticReport> reports = new ArrayList<>();
        for(Long id: ids) {
            DiagnosticReport report = new DiagnosticReport();
            report.setId(id);
            report.setIssuedTime(new Timestamp(System.currentTimeMillis()));
            report.setTitle("Title");
            report.setDescription("Description");
            report.setPropApplId((long) 1);
            reports.add(report);
        }

        repository.addItems(reports);
    }

    @Override
    public void get(Long id, IErrorCallback errorCallback) {
        IDiagnosticReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticReportRepository();

        DiagnosticReport report = new DiagnosticReport();
        report.setId(id);
        report.setIssuedTime(new Timestamp(System.currentTimeMillis()));
        report.setTitle("Title");
        report.setDescription("Description");
        report.setPropApplId((long) 1);
        repository.addItem(report);
    }
}
