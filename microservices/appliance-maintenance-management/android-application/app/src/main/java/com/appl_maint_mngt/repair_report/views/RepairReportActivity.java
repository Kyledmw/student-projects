package com.appl_maint_mngt.repair_report.views;

import android.os.Bundle;
import android.view.View;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.LoggingErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.views.utility.DiagnosticReportIntentBuilder;
import com.appl_maint_mngt.maintenance_schedule.models.interfaces.IMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.utility.PendingMaintenanceSchedulingListFilter;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.views.constants.IRepairReportViewConstants;
import com.appl_maint_mngt.repair_report.views.interfaces.IRepairReportView;

import java.util.List;
import java.util.Observable;

public class RepairReportActivity extends ACommonActivity {

    private Long repairReportId;

    private IRepairReportView repairReportView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_report);


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.repairReportId = extras.getLong(IRepairReportViewConstants.ID_KEY);
        }

        repairReportView = new RepairReportView(this);
        repairReportView.setDiagnosticReportOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IRepairReportReadable repairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getForId(repairReportId);
                startActivity(new DiagnosticReportIntentBuilder().build(RepairReportActivity.this, repairReport.getDiagnosticReportId()));
            }
        });
        updateView();
    }

    @Override
    public void updateModels() {
        if(repairReportId != null) {
            IRepairReportReadable repairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getForId(repairReportId);
            IntegrationController.getInstance().getControllerFactory().createDiagnosticReportController().getForDiagnosticReportId(repairReport.getDiagnosticReportId(), new LoggingErrorCallback());
            IntegrationController.getInstance().getControllerFactory().createMaintenanceScheduleController().getForRepairReport(repairReportId, new LoggingErrorCallback());
            IntegrationController.getInstance().getControllerFactory().createPendingMaintenanceSchedulingController().getAllPendingScheduledByEngineer(repairReportId, new LoggingErrorCallback());
            IntegrationController.getInstance().getControllerFactory().createPendingMaintenanceSchedulingController().getAllPendingScheduledByManager(repairReportId, new LoggingErrorCallback());
        }
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeRepairReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeDiagnosticReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeMaintenanceScheduleRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingMaintenanceSchedulingRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveRepairReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveDiagnosticReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveMaintenanceScheduleRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingMaintenanceSchedulingRepository(this);
    }

    @Override
    protected void updateView() {
        if(repairReportView == null) return;
        IRepairReportReadable repairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getForId(repairReportId);
        if(repairReport == null) return;
        repairReportView.update(repairReport);

        IDiagnosticReportReadable diagReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticReportRepository().get(repairReport.getDiagnosticReportId());
        if(diagReport == null) {
            repairReportView.disableDiagnosticReportButton();
        } else {
            repairReportView.enableDiagnosticReportButton();
        }

        IMaintenanceScheduleReadable maintenanceSchedule = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceScheduleReadableRepository().getForReportId(repairReportId);
        if(maintenanceSchedule != null) {
            repairReportView.updateMaintenanceSchedule(maintenanceSchedule);
            repairReportView.showMaintenanceSchedule();
            repairReportView.hidePendingMaintenanceSchedules();
        } else {
            repairReportView.hideMaintenanceSchedule();
            repairReportView.showPendingMaintenanceSchedules();
            List<IPendingMaintenanceScheduleReadable> list = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingMaintenanceSchedulingReadableRepository().getForRepairReportId(repairReportId);
            list = new PendingMaintenanceSchedulingListFilter().filterOnScheduleStatus(list, ScheduleStatus.PENDING);
            repairReportView.updatePendingMaintenanceSchedules(list);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
