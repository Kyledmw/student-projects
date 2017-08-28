package com.appl_maint_mngt.pending_repair_report.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.pending_repair_report.events.constants.IPendingRepairReportEvents;
import com.appl_maint_mngt.pending_repair_report.models.PendingRepairReport;
import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;
import com.appl_maint_mngt.pending_repair_report.models.web.interfaces.IPendingRepairReportPayload;
import com.appl_maint_mngt.pending_repair_report.repositories.interfaces.IPendingRepairReportUpdateableRepository;
import com.appl_maint_mngt.pending_repair_report.services.interfaces.IPendingRepairReportService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public class DummyPendingRepairReportService implements IPendingRepairReportService {
    @Override
    public void accept(Long id, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setTitle("Title");
        pendingRepairReport.setOnSite(true);
        pendingRepairReport.setEstimatedDurationHours(5);
        pendingRepairReport.setId(id);
        pendingRepairReport.setCost(new BigDecimal(500));
        pendingRepairReport.setDescription("Description");
        pendingRepairReport.setDiagnosticRequestId((long) 1);
        pendingRepairReport.setEngineerId((long) 1);
        pendingRepairReport.setResponseStatus(ResponseStatus.ACCEPTED);

        ApplicationEventBus.getInstance().sendEvent(IPendingRepairReportEvents.ACCEPT_EVENT);
        repository.addItem(pendingRepairReport);
    }

    @Override
    public void decline(Long id, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setTitle("Title");
        pendingRepairReport.setOnSite(true);
        pendingRepairReport.setEstimatedDurationHours(5);
        pendingRepairReport.setId(id);
        pendingRepairReport.setCost(new BigDecimal(500));
        pendingRepairReport.setDescription("Description");
        pendingRepairReport.setDiagnosticRequestId((long) 1);
        pendingRepairReport.setEngineerId((long) 1);
        pendingRepairReport.setResponseStatus(ResponseStatus.DECLINED);

        ApplicationEventBus.getInstance().sendEvent(IPendingRepairReportEvents.DECLINE_EVENT);
        repository.addItem(pendingRepairReport);
    }

    @Override
    public void findByDiagnosticRequestId(Long diagnosticRequestId, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setTitle("Title");
        pendingRepairReport.setOnSite(true);
        pendingRepairReport.setEstimatedDurationHours(5);
        pendingRepairReport.setId(diagnosticRequestId);
        pendingRepairReport.setCost(new BigDecimal(500));
        pendingRepairReport.setDescription("Description");
        pendingRepairReport.setDiagnosticRequestId((long) 1);
        pendingRepairReport.setEngineerId((long) 1);
        pendingRepairReport.setResponseStatus(ResponseStatus.DECLINED);

        repository.addItem(pendingRepairReport);
    }

    @Override
    public void findByDiagnosticRequestIdIn(List<Long> ids, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        List<PendingRepairReport> list = new ArrayList<>();
        long counter = 1;
        for(Long id: ids) {
            PendingRepairReport pendingRepairReport = new PendingRepairReport();
            pendingRepairReport.setTitle("Title");
            pendingRepairReport.setOnSite(true);
            pendingRepairReport.setEstimatedDurationHours(5);
            pendingRepairReport.setId(counter);
            pendingRepairReport.setCost(new BigDecimal(500));
            pendingRepairReport.setDescription("Description");
            pendingRepairReport.setDiagnosticRequestId(id);
            pendingRepairReport.setEngineerId((long) 1);
            pendingRepairReport.setResponseStatus(ResponseStatus.PENDING);
            counter++;
            list.add(pendingRepairReport);
        }
        repository.addItems(list);
    }

    @Override
    public void create(IPendingRepairReportPayload payload, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setEngineerId(payload.getEngineerId());
        pendingRepairReport.setDiagnosticRequestId(payload.getDiagnosticRequestId());
        pendingRepairReport.setId((long) 1);
        pendingRepairReport.setOnSite(payload.isOnSite());
        pendingRepairReport.setCost(payload.getCost());
        pendingRepairReport.setDescription(payload.getDescription());
        pendingRepairReport.setEstimatedDurationHours(payload.getEstimatedDurationHours());
        pendingRepairReport.setTitle(payload.getTitle());
        pendingRepairReport.setResponseStatus(ResponseStatus.PENDING);

        repository.addItem(pendingRepairReport);
    }

    @Override
    public void findByEngineerId(Long engineerId, IErrorCallback errorCallback) {
        IPendingRepairReportUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();

        PendingRepairReport pendingRepairReport = new PendingRepairReport();
        pendingRepairReport.setTitle("Title");
        pendingRepairReport.setOnSite(true);
        pendingRepairReport.setEstimatedDurationHours(5);
        pendingRepairReport.setId((long) 1);
        pendingRepairReport.setCost(new BigDecimal(500));
        pendingRepairReport.setDescription("Description");
        pendingRepairReport.setDiagnosticRequestId((long) 1);
        pendingRepairReport.setEngineerId(engineerId);
        pendingRepairReport.setResponseStatus(ResponseStatus.DECLINED);

        repository.addItem(pendingRepairReport);
    }
}
