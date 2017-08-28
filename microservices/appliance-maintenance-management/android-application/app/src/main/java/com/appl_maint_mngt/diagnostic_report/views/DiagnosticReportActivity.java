package com.appl_maint_mngt.diagnostic_report.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.common.errors.DialogErrorCallback;
import com.appl_maint_mngt.common.errors.LoggingErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.views.constants.IDiagnosticReportViewConstants;
import com.appl_maint_mngt.diagnostic_report.views.interfaces.IDiagnosticReportView;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.views.SendDiagnosticRequestsDialog;
import com.appl_maint_mngt.diagnostic_request.views.utility.PropertyManagerDiagnosticRequestListItemClickListener;
import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.maintenance_organisation.utility.MaintenanceOrganisationIDListGenerator;
import com.appl_maint_mngt.maintenance_organisation.utility.MaintenanceOrganisationListFilter;
import com.appl_maint_mngt.property_appliance.views.utility.PropertyApplianceIntentBuilder;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.views.utility.RepairReportIntentBuilder;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.List;
import java.util.Observable;

public class DiagnosticReportActivity extends ACommonActivity {
    private static final Logger logger = LoggerManager.getLogger(DiagnosticReportActivity.class);

    private Long diagnosticReportId;
    private IDiagnosticReportView diagnosticReportView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic_report);
        logger.i("Creating DiagnosticReportActivity");

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.diagnosticReportId = extras.getLong(IDiagnosticReportViewConstants.ID_KEY);
            logger.i("DiagnosticReport for %d: ", diagnosticReportId);
        }

        diagnosticReportView = new DiagnosticReportView(this);
        diagnosticReportView.setSendDiagnosticRequestsOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<IMaintenanceOrganisationReadable> allMaintOrgs = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceOrganisationRepository().getAll();
                List<IDiagnosticRequestReadable> diagnosticRequests = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticRequestRepository().getForDiagnosticReportId(diagnosticReportId);
                List<Long> maintOrgIds = new MaintenanceOrganisationIDListGenerator().generateForDiagnosticRequests(diagnosticRequests);
                final List<IMaintenanceOrganisationReadable> maintOrgs = new MaintenanceOrganisationListFilter().filterIds(allMaintOrgs, maintOrgIds);
                if(!maintOrgs.isEmpty()) {
                    final SendDiagnosticRequestsDialog dialog = new SendDiagnosticRequestsDialog(DiagnosticReportActivity.this, maintOrgs);
                    dialog.setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int which) {
                            IntegrationController.getInstance().getControllerFactory().createDiagnosticRequestController().createRequests(diagnosticReportId, dialog.getSelected(), new DialogErrorCallback(DiagnosticReportActivity.this));
                            dialog.close();
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
            }
        });

        diagnosticReportView.setOnDiagnosticRequestsItemClickListener(new PropertyManagerDiagnosticRequestListItemClickListener(this));

        diagnosticReportView.setViewRelatedActivityClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
                if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
                    IDiagnosticReportReadable diagnosticReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticReportRepository().get(diagnosticReportId);
                    startActivity(new PropertyApplianceIntentBuilder().build(DiagnosticReportActivity.this, diagnosticReport.getPropApplId()));
                } else if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
                    IRepairReportReadable repairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getForDiagnosticReportId(diagnosticReportId);
                    startActivity(new RepairReportIntentBuilder().build(DiagnosticReportActivity.this, repairReport.getId()));
                }
            }
        });
    }

    @Override
    public void updateModels() {
        if(diagnosticReportId != null) {
            IntegrationController.getInstance().getControllerFactory().createDiagnosticReportController().getForDiagnosticReportId(diagnosticReportId, new LoggingErrorCallback());
            IDiagnosticReportReadable diagnosticReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticReportRepository().get(diagnosticReportId);
            IntegrationController.getInstance().getControllerFactory().createPropertyApplianceController().getPropertyApplianceForId(diagnosticReport.getPropApplId(), new LoggingErrorCallback());
            IntegrationController.getInstance().getControllerFactory().createDiagnosticRequestController().getForDiagnosticReportId(diagnosticReportId, new LoggingErrorCallback());
            IntegrationController.getInstance().getControllerFactory().createRepairReportController().getForDiagnosticId(diagnosticReportId, new LoggingErrorCallback());
        }
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeDiagnosticReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeDiagnosticRequestRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeRepairReportRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveDiagnosticReportRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveDiagnosticRequestRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveRepairReportRepository(this);
    }

    @Override
    protected void updateView() {
        IDiagnosticReportReadable diagnosticReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticReportRepository().get(diagnosticReportId);
        diagnosticReportView.update(diagnosticReport);

        List<IDiagnosticRequestReadable> diagReqList = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticRequestRepository().getForDiagnosticReportId(diagnosticReportId);
        diagnosticReportView.updateDiagnosticRequests(diagReqList);

        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            diagnosticReportView.setRelatedActivityBtnText(R.string.repair_report_action_view);
            IRepairReportReadable repairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getForDiagnosticReportId(diagnosticReportId);
            logger.i("Getting RepairReport for diagnostic report id: ID: %d", diagnosticReportId);
            if(repairReport == null) {
                logger.i("RepairReport was null");
                diagnosticReportView.hideViewRelatedActivityBtn();
                diagnosticReportView.displayDiagnosticRequests();
            } else {
                diagnosticReportView.displayViewRelatedActivityBtn();
                diagnosticReportView.hideDiagnosticRequests();
            }
        } else if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)){
            diagnosticReportView.setRelatedActivityBtnText(R.string.property_appliance_action_view);
            diagnosticReportView.displayViewRelatedActivityBtn();
            diagnosticReportView.hideDiagnosticRequests();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
