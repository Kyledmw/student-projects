package com.appl_maint_mngt.property_tenant.views;

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
import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance_schedule.views.utility.MaintenanceScheduleIntentBuilder;
import com.appl_maint_mngt.property.views.utility.PropertyIntentBuilder;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.utility.PropertyApplianceIDListGenerator;
import com.appl_maint_mngt.property_tenant.models.interfaces.IPropertyTenantReadable;
import com.appl_maint_mngt.property_tenant.repositories.constants.IPropertyTenantObserverUpdateTypes;
import com.appl_maint_mngt.property_tenant.views.interfaces.IPropertyTenantDashboardView;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.utility.RepairReportIDListGenerator;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.List;
import java.util.Observable;

public class PropertyTenantDashboardActivity extends ANFCActivity {
    private static final Logger logger = LoggerManager.getLogger(PropertyTenantDashboardActivity.class);

    private IPropertyTenantDashboardView propertyTenantDashboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_tenant_dashboard);

        propertyTenantDashboardView = new PropertyTenantDashboardView(this);
        propertyTenantDashboardView.setPropertyOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyTenantRepository().get().getCurrentPropertyId();
                Intent propertyIntent = new PropertyIntentBuilder().build(PropertyTenantDashboardActivity.this, IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyTenantRepository().get().getCurrentPropertyId());
                startActivity(propertyIntent);
            }
        });

        propertyTenantDashboardView.setMaintenanceScheduleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new MaintenanceScheduleIntentBuilder().build(PropertyTenantDashboardActivity.this));
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
        IntegrationController.getInstance().getControllerFactory().createPropertyTenantController().getPropertyTenant(accountRepository.get().getId(), new DialogErrorCallback(this));
        IPropertyTenantReadable propTenant = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyTenantRepository().get();
        if(propTenant.getCurrentPropertyId() != null) {
            IntegrationController.getInstance().getControllerFactory().createPropertyController().getProperty(propTenant.getCurrentPropertyId(), new DialogErrorCallback(this));
            IntegrationController.getInstance().getControllerFactory().createPropertyApplianceController().getPropertyAppliancesForProperty(propTenant.getCurrentPropertyId(), new DialogErrorCallback(this));
        }
        IntegrationController.getInstance().getControllerFactory().createApplianceStatusController().getAll(new DialogErrorCallback(this));
        IntegrationController.getInstance().getControllerFactory().createMaintenanceOrganisationController().getAll(new DialogErrorCallback(this));

        IntegrationController.getInstance().getControllerFactory().createApplianceController().getAll(new LoggingErrorCallback());

        List<IPropertyApplianceReadable> propertyAppliances = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyApplianceRepository().getAll();
        IntegrationController.getInstance().getControllerFactory().createDiagnosticReportController().getForPropertyAppliances(new PropertyApplianceIDListGenerator().generate(propertyAppliances), new DialogErrorCallback(this));
        List<IDiagnosticReportReadable> diagnosticReports = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticReportRepository().getAll();
        IntegrationController.getInstance().getControllerFactory().createRepairReportController().getForDiagnosticIds(new DiagnosticReportIDListGenerator().generate(diagnosticReports), new DialogErrorCallback(this));
        List<IRepairReportReadable> repairReports = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getAll();
        IntegrationController.getInstance().getControllerFactory().createMaintenanceScheduleController().getForRepairReports(new RepairReportIDListGenerator().generate(repairReports), new DialogErrorCallback(this));
    }

    @Override
    protected void startObserving() {
        logger.i("startObserving");
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePropertyTenantRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeApplianceRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeMaintenanceScheduleRepository(this);
    }

    @Override
    protected void stopObserving() {
        logger.i("stopObserving");
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePropertyTenantRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveApplianceRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveMaintenanceScheduleRepository(this);
    }

    @Override
    protected void updateView() {
        logger.i("Updating View");
        if(propertyTenantDashboardView == null) return;
        IPropertyTenantReadable propTenant = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyTenantRepository().get();
        if(propTenant.getCurrentPropertyId() != null) {
            propertyTenantDashboardView.disablePropertyButton();
            if(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyRepository().getForId(propTenant.getCurrentPropertyId()) != null) {
                propertyTenantDashboardView.enablePropertyButton();
            }
        }

        if(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceScheduleReadableRepository().getForStatus(ScheduleStatus.NORMAL).isEmpty()) {
            propertyTenantDashboardView.disableMaintenanceScheduleButton();
        } else {
            propertyTenantDashboardView.enableMaintenanceScheduleButton();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.i("Received update from observable");
        updateView();
        if(arg.equals(IPropertyTenantObserverUpdateTypes.MODEL_UPDATE)) {
            logger.i("Entered Update: %s", IPropertyTenantObserverUpdateTypes.MODEL_UPDATE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent loginIntent = new AccountIntentBuilder().buildLogin(this);
        startActivity(loginIntent);
    }
}
