package com.appl_maint_mngt.diagnostic_request.views;

import android.os.Bundle;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.common.errors.LoggingErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.utility.DiagnosticRequestsRetriever;
import com.appl_maint_mngt.diagnostic_request.views.interfaces.IDiagnosticRequestListView;
import com.appl_maint_mngt.diagnostic_request.views.utility.DiagnosticRequestListAdapterDiagnosticReport;
import com.appl_maint_mngt.diagnostic_request.views.utility.DiagnosticRequestListAdapterMaintenanceOrganisation;
import com.appl_maint_mngt.diagnostic_request.views.utility.MaintenanceEngineerDiagnosticRequestListitemClickListener;
import com.appl_maint_mngt.diagnostic_request.views.utility.PropertyManagerDiagnosticRequestListItemClickListener;
import com.appl_maint_mngt.maintenance_engineer.models.interfaces.IMaintenanceEngineerReadable;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.ArrayList;
import java.util.Observable;

public class DiagnosticRequestsListActivity extends ACommonActivity {
    private static final Logger logger = LoggerManager.getLogger(DiagnosticRequestsListActivity.class);

    private IDiagnosticRequestListView diagnosticRequestListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic_requests_list);
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            diagnosticRequestListView = new DiagnosticRequestListView(this, new DiagnosticRequestListAdapterMaintenanceOrganisation(this, new ArrayList<IDiagnosticRequestReadable>()));
            diagnosticRequestListView.setOnDiagnosticRequestSelelectListener(new PropertyManagerDiagnosticRequestListItemClickListener(this));
        } else if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
            diagnosticRequestListView = new DiagnosticRequestListView(this, new DiagnosticRequestListAdapterDiagnosticReport(this, new ArrayList<IDiagnosticRequestReadable>()));
            diagnosticRequestListView.setOnDiagnosticRequestSelelectListener(new MaintenanceEngineerDiagnosticRequestListitemClickListener(this));
        }
        updateView();
    }

    @Override
    public void updateModels() {
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
            IMaintenanceEngineerReadable maintEng = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceEngineerRepository().get();
            IntegrationController.getInstance().getControllerFactory().createDiagnosticRequestController().getForMaintenanceOrgId(maintEng.getCurrentOrganisationId(), new LoggingErrorCallback());
        }
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeDiagnosticRequestRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveDiagnosticRequestRepository(this);
    }

    @Override
    protected void updateView() {
        logger.i("UpdateView");
        if(diagnosticRequestListView == null) return;
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            logger.i("Updating Diagnostic Request");
            diagnosticRequestListView.update(new DiagnosticRequestsRetriever().getPendingAndResponded());
        } else if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
            diagnosticRequestListView.update(new DiagnosticRequestsRetriever().getPending());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
