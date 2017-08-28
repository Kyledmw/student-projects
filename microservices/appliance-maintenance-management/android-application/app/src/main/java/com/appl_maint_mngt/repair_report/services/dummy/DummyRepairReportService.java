package com.appl_maint_mngt.repair_report.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.repair_report.models.RepairReport;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.repositories.interfaces.IRepairReportUpdateableRepository;
import com.appl_maint_mngt.repair_report.services.interfaces.IRepairReportService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public class DummyRepairReportService implements IRepairReportService {
    @Override
    public void findByDiagnosticReportId(Long id, IErrorCallback errorCallback) {
        IRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getRepairReportRepository();
        RepairReport repairReport = new RepairReport();
        repairReport.setId((long) 1);
        repairReport.setCost(new BigDecimal(500));
        repairReport.setDescription("Description");
        repairReport.setDiagnosticReportId(id);
        repairReport.setEngineerId((long) 1);
        repairReport.setEstimatedDurationHours(5);
        repairReport.setOnSite(true);
        repairReport.setTitle("Title");
        repository.addItem(repairReport);
    }

    @Override
    public void findByDiagnosticReportIdsIn(List<Long> ids, IErrorCallback errorCallback) {
        IRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getRepairReportRepository();
        List<RepairReport> list = new ArrayList<>();
        long count = 1;
        for(Long id: ids) {
            RepairReport repairReport = new RepairReport();
            repairReport.setId(count);
            repairReport.setCost(new BigDecimal(500));
            repairReport.setDescription("Description");
            repairReport.setDiagnosticReportId(id);
            repairReport.setEngineerId((long) 1);
            repairReport.setEstimatedDurationHours(5);
            repairReport.setOnSite(true);
            repairReport.setTitle("Title");
            list.add(repairReport);
            count++;
        }
        repository.addItems(list);
    }

    @Override
    public void findByEngineerId(Long engineerId, IErrorCallback errorCallback) {
        IRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getRepairReportRepository();
        RepairReport repairReport = new RepairReport();
        repairReport.setId((long) 1);
        repairReport.setCost(new BigDecimal(500));
        repairReport.setDescription("Description");
        repairReport.setDiagnosticReportId((long) 1);
        repairReport.setEngineerId(engineerId);
        repairReport.setEstimatedDurationHours(5);
        repairReport.setOnSite(true);
        repairReport.setTitle("Title");
        repository.addItem(repairReport);
    }
}
