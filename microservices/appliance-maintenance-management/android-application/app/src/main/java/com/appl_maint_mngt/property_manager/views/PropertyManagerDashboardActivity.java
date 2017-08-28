package com.appl_maint_mngt.property_manager.views;

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
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.utility.DiagnosticReportIDListGenerator;
import com.appl_maint_mngt.diagnostic_request.utility.DiagnosticRequestIDListGenerator;
import com.appl_maint_mngt.diagnostic_request.utility.DiagnosticRequestIntentBuilder;
import com.appl_maint_mngt.diagnostic_request.utility.DiagnosticRequestsRetriever;
import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance_schedule.views.utility.MaintenanceScheduleIntentBuilder;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.views.utility.PendingMaintenanceSchedulingIntentBuilder;
import com.appl_maint_mngt.pending_repair_report.utility.PendingRepairReportRetriever;
import com.appl_maint_mngt.pending_repair_report.views.utility.PendingRepairReportIntentBuilder;
import com.appl_maint_mngt.property.repositories.constants.IPropertyObserverUpdateTypes;
import com.appl_maint_mngt.property.views.PropertyListActivity;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.utility.PropertyApplianceIDListGenerator;
import com.appl_maint_mngt.property_manager.models.interfaces.IPropertyManagerReadable;
import com.appl_maint_mngt.property_manager.repositories.constants.IPropertyManagerObserverUpdateTypes;
import com.appl_maint_mngt.property_manager.views.interfaces.IPropertyManagerDashboardView;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.utility.RepairReportIDListGenerator;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.List;
import java.util.Observable;

public class PropertyManagerDashboardActivity extends ANFCActivity {
    private static final Logger logger = LoggerManager.getLogger(PropertyManagerDashboardActivity.class);

    private IPropertyManagerDashboardView propertyManagerDashboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_manager_dashboard);

        propertyManagerDashboardView = new PropertyManangerDashboardView(this);
        propertyManagerDashboardView.setPropertiesOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent propertyListIntent = new Intent(PropertyManagerDashboardActivity.this, PropertyListActivity.class);
                startActivity(propertyListIntent);
            }
        });
        propertyManagerDashboardView.setDiagnosticRequestsOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new DiagnosticRequestIntentBuilder().buildListIntent(PropertyManagerDashboardActivity.this));
            }
        });

        propertyManagerDashboardView.setMaintenanceScheduleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new MaintenanceScheduleIntentBuilder().build(PropertyManagerDashboardActivity.this));
            }
        });

        propertyManagerDashboardView.setPendingRepairReportsOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new PendingRepairReportIntentBuilder().buildList(PropertyManagerDashboardActivity.this));
            }
        });

        propertyManagerDashboardView.setPendingMaintenanceScheduleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new PendingMaintenanceSchedulingIntentBuilder().buildList(PropertyManagerDashboardActivity.this));
            }
        });
        updateModels();
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
        IntegrationController.getInstance().getControllerFactory().createPropertyManangerController().getPropertyManager(accountRepository.get().getId(), new LoggingErrorCallback());
        IPropertyManagerReadable propManager = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyManangerRepository().get();
        IntegrationController.getInstance().getControllerFactory().createPropertyController().getProperties(propManager.getCurrentPropertyIds(), new LoggingErrorCallback());

        IntegrationController.getInstance().getControllerFactory().createPropertyApplianceController().getPropertyAppliancesForProperties(propManager.getCurrentPropertyIds(), new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createApplianceStatusController().getAll(new LoggingErrorCallback());

        List<IPropertyApplianceReadable> propertyApplianceList = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyApplianceRepository().getAll();
        IntegrationController.getInstance().getControllerFactory().createDiagnosticReportController().getForPropertyAppliances(new PropertyApplianceIDListGenerator().generate(propertyApplianceList), new LoggingErrorCallback());

        IntegrationController.getInstance().getControllerFactory().createMaintenanceOrganisationController().getAll(new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createApplianceController().getAll(new LoggingErrorCallback());

        List<IDiagnosticReportReadable> diagReportList = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticReportRepository().getAll();
        IntegrationController.getInstance().getControllerFactory().createDiagnosticRequestController().getForDiagnosticReportIds(new DiagnosticReportIDListGenerator().generate(diagReportList), new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createRepairReportController().getForDiagnosticIds(new DiagnosticReportIDListGenerator().generate(diagReportList), new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createPendingRepairReportController().getForDiagnosticRequests(new DiagnosticRequestIDListGenerator().generate(new DiagnosticRequestsRetriever().getPendingAndResponded()),  new LoggingErrorCallback());
        List<IRepairReportReadable> repairReports = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getAll();
        IntegrationController.getInstance().getControllerFactory().createMaintenanceScheduleController().getForRepairReports(new RepairReportIDListGenerator().generate(repairReports), new LoggingErrorCallback());
        IntegrationController.getInstance().getControllerFactory().createPendingMaintenanceSchedulingController().getAllPendingForReportIds(new RepairReportIDListGenerator().generate(repairReports), new LoggingErrorCallback());
    }

    @Override
    protected void startObserving() {
        logger.i("startObserving");
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observPropertyManagerRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePropertyRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeDiagnosticRequestRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingRepairReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeMaintenanceScheduleRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingMaintenanceSchedulingRepository(this);
    }

    @Override
    protected void stopObserving() {
        logger.i("stopObserving");
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePropertyManangerRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePropertyRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveDiagnosticRequestRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingRepairReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveMaintenanceScheduleRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingMaintenanceSchedulingRepository(this);
    }

    @Override
    protected void updateView() {
        logger.i("Updating View");
        if(propertyManagerDashboardView == null) return;
        if(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyRepository().getAll().isEmpty()) {
            propertyManagerDashboardView.disablePropertiesButton();
        } else {
            propertyManagerDashboardView.enablePropertiesButton();
        }

        if(new DiagnosticRequestsRetriever().getPendingAndResponded().isEmpty()) {
            propertyManagerDashboardView.disableDiagnosticRequestsButton();
        } else {
            propertyManagerDashboardView.enableDiagnosticRequestsButton();
        }

        if(new PendingRepairReportRetriever().retrievePending().isEmpty()) {
            propertyManagerDashboardView.disablePendingRepairReportButton();
        } else {
            propertyManagerDashboardView.enablePendingRepairReportButton();
        }

        if(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceScheduleReadableRepository().getForStatus(ScheduleStatus.NORMAL).isEmpty()) {
            propertyManagerDashboardView.disableMaintenanceScheduleButton();
        } else {
            propertyManagerDashboardView.enableMaintenanceScheduleButton();
        }

        if(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingMaintenanceSchedulingReadableRepository().getForScheduler(SchedulerType.ENGINEER_REPRESENTITIVE).isEmpty()) {
            propertyManagerDashboardView.disablePendingMaintenanceScheduleButton();
        } else {
            propertyManagerDashboardView.enablePendingMaintenanceScheduleButton();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.i("Received update from observable");
        updateView();
        if(arg.equals(IPropertyManagerObserverUpdateTypes.MODEL_UPDATE)) {
            logger.i("Entered Update: %s", IPropertyManagerObserverUpdateTypes.MODEL_UPDATE);
        } else if(arg.equals(IPropertyObserverUpdateTypes.NEW_ITEM_UPDATE)) {
            logger.i("Entered Update: %s", IPropertyObserverUpdateTypes.NEW_ITEM_UPDATE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent loginIntent = new AccountIntentBuilder().buildLogin(this);
        startActivity(loginIntent);
    }
}
