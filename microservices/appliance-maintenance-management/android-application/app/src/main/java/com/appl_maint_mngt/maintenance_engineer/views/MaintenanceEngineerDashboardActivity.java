package com.appl_maint_mngt.maintenance_engineer.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.repositories.interfaces.IAccountReadableRepository;
import com.appl_maint_mngt.account.views.utility.AccountIntentBuilder;
import com.appl_maint_mngt.common.errors.DialogErrorCallback;
import com.appl_maint_mngt.common.errors.LoggingErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.common.views.ANFCActivity;
import com.appl_maint_mngt.diagnostic_report.utility.DiagnosticReportIDListGenerator;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.utility.DiagnosticRequestIntentBuilder;
import com.appl_maint_mngt.diagnostic_request.utility.DiagnosticRequestsRetriever;
import com.appl_maint_mngt.maintenance_engineer.models.interfaces.IMaintenanceEngineerReadable;
import com.appl_maint_mngt.maintenance_engineer.repositories.constants.IMaintenanceEngineerObserverUpdateTypes;
import com.appl_maint_mngt.maintenance_engineer.views.interfaces.IMaintenanceEngineerDashboardView;
import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance_schedule.views.utility.MaintenanceScheduleIntentBuilder;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.views.utility.PendingMaintenanceSchedulingIntentBuilder;
import com.appl_maint_mngt.pending_repair_report.utility.PendingRepairReportRetriever;
import com.appl_maint_mngt.pending_repair_report.views.utility.PendingRepairReportIntentBuilder;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.utility.RepairReportIDListGenerator;
import com.appl_maint_mngt.repair_report.views.utility.RepairReportIntentBuilder;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.List;
import java.util.Observable;

public class MaintenanceEngineerDashboardActivity extends ANFCActivity {
    private static final Logger logger = LoggerManager.getLogger(MaintenanceEngineerDashboardActivity.class);

    private IMaintenanceEngineerDashboardView maintenanceEngineerDashboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_engineer_dashboard);
        logger.i("Creating MaintenanceEngineerDashboardActivity");

        maintenanceEngineerDashboardView = new MaintenanceEngineerDashboardView(this);
        maintenanceEngineerDashboardView.setDiagnosticRequestsOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new DiagnosticRequestIntentBuilder().buildListIntent(MaintenanceEngineerDashboardActivity.this));
            }
        });
        maintenanceEngineerDashboardView.setMaintenanceSchedulingOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new MaintenanceScheduleIntentBuilder().build(MaintenanceEngineerDashboardActivity.this));
            }
        });

        maintenanceEngineerDashboardView.setPendingRepairReportsOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new PendingRepairReportIntentBuilder().buildList(MaintenanceEngineerDashboardActivity.this));
            }
        });

        maintenanceEngineerDashboardView.setRepairReportsOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new RepairReportIntentBuilder().buildList(MaintenanceEngineerDashboardActivity.this));
            }
        });

        maintenanceEngineerDashboardView.setPendingMaintenanceSchedulingOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new PendingMaintenanceSchedulingIntentBuilder().buildList(MaintenanceEngineerDashboardActivity.this));
            }
        });

        updateView();
    }

    @Override
    public void onResume() {
        super.onResume();
        logger.i("Entered onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        logger.i("Entered onPause()");
    }

    @Override
    public void updateModels() {
        IAccountReadableRepository accountRepository = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository();
        IntegrationController.getInstance().getControllerFactory().createMaintenanceEngineerController().getEngineer(accountRepository.get().getId(), new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createApplianceStatusController().getAll(new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createMaintenanceOrganisationController().getAll(new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createRepairReportController().getForEngineer(accountRepository.get().getId(), new LoggingErrorCallback());
        IMaintenanceEngineerReadable maintEng = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceEngineerRepository().get();
        if(maintEng != null) {
            IntegrationController.getInstance().getControllerFactory().createDiagnosticRequestController().getForMaintenanceOrgId(maintEng.getCurrentOrganisationId(), new LoggingErrorCallback());
        }
        List<IDiagnosticRequestReadable> diagnosticRequests = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticRequestRepository().getAll();
        IntegrationController.getInstance().getControllerFactory().createDiagnosticReportController().getForDiagnosticReportIds(new DiagnosticReportIDListGenerator().generateForRequests(diagnosticRequests), new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createPendingRepairReportController().getForEngineer(accountRepository.get().getId(), new LoggingErrorCallback());

        List<IRepairReportReadable> repairReports = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getAll();
        IntegrationController.getInstance().getControllerFactory().createMaintenanceScheduleController().getForRepairReports(new RepairReportIDListGenerator().generate(repairReports), new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createPendingMaintenanceSchedulingController().getAllPendingForReportIds(new RepairReportIDListGenerator().generate(repairReports), new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createApplianceController().getAll(new LoggingErrorCallback());
    }

    @Override
    protected void startObserving() {
        logger.i("startObserving");
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeMaintenanceEngineerRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeDiagnosticRequestRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeRepairReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingRepairReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeMaintenanceScheduleRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingMaintenanceSchedulingRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeApplianceRepository(this);
    }

    @Override
    protected void stopObserving() {
        logger.i("stopObserving");
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveMaintenanceEngineerRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveDiagnosticRequestRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveRepairReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingRepairReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveMaintenanceScheduleRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingMaintenanceSchedulingRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveApplianceRepository(this);
    }

    @Override
    protected void updateView() {
        logger.i("Updating View");
        if(maintenanceEngineerDashboardView == null) return;

        if(new DiagnosticRequestsRetriever().getPending().isEmpty()) {
            maintenanceEngineerDashboardView.disableDiagnosticRequestsButton();
        } else {
            maintenanceEngineerDashboardView.enableDiagnosticRequestsButton();
        }

        if(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getAll().isEmpty()) {
            maintenanceEngineerDashboardView.disableRepairReportsButton();
        } else {
            maintenanceEngineerDashboardView.enableRepairReportsButton();
        }

        if(new PendingRepairReportRetriever().retrievePendingAndDeclined().isEmpty()) {
            maintenanceEngineerDashboardView.disablePendingRepairReportsButton();
        } else {
            maintenanceEngineerDashboardView.enablePendingRepairReportsButton();
        }

        if(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceScheduleReadableRepository().getForStatus(ScheduleStatus.NORMAL).isEmpty()) {
            maintenanceEngineerDashboardView.disableMaintenanceSchedulingButton();
        } else {
            maintenanceEngineerDashboardView.enableMaintenanceSchedulingButton();
        }

        if(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingMaintenanceSchedulingReadableRepository().getForScheduler(SchedulerType.PROPERTY_REPRESENTITIVE).isEmpty()) {
            maintenanceEngineerDashboardView.disablePendingMaintenanceSchedulingButton();
        } else {
            maintenanceEngineerDashboardView.enablePendingMaintenanceSchedulingButton();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.i("Received update from observable");
        if(arg.equals(IMaintenanceEngineerObserverUpdateTypes.MODEL_UPDATE)) {
            logger.i("Entered Update: %s", IMaintenanceEngineerObserverUpdateTypes.MODEL_UPDATE);
            updateView();
        }
    }

    @Override
    public void onBackPressed() {
        Intent loginIntent = new AccountIntentBuilder().buildLogin(this);
        startActivity(loginIntent);
    }
}
