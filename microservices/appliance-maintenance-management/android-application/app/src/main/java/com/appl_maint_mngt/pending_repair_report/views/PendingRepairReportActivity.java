package com.appl_maint_mngt.pending_repair_report.views;

import android.os.Bundle;
import android.view.View;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.common.errors.LoggingErrorCallback;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.pending_repair_report.events.constants.IPendingRepairReportEvents;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.views.constants.IPendingRepairReportViewConstants;
import com.appl_maint_mngt.pending_repair_report.views.interfaces.IPendingRepairReportView;
import com.appl_maint_mngt.property_manager.views.utility.PropertyManagerIntentBuilder;

import java.util.Observable;

public class PendingRepairReportActivity extends ACommonActivity {

    private Long pendingRepairReportId;

    private IPendingRepairReportView pendingRepairReportView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_repair_report);

        pendingRepairReportView = new PendingRepairReportView(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            pendingRepairReportId = extras.getLong(IPendingRepairReportViewConstants.ID_KEY);
        }

        pendingRepairReportView.setOnAcceptListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPendingRepairReportReadable pendingRepairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingRepairReportReadableRepository().getForId(pendingRepairReportId);
                IntegrationController.getInstance().getControllerFactory().createPendingRepairReportController().acceptPendingRepairReport(pendingRepairReport, new LoggingErrorCallback());
            }
        });

        pendingRepairReportView.setOnDeclineListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPendingRepairReportReadable pendingRepairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingRepairReportReadableRepository().getForId(pendingRepairReportId);
                IntegrationController.getInstance().getControllerFactory().createPendingRepairReportController().declinePendingRepairReport(pendingRepairReport,new LoggingErrorCallback());
            }
        });
        updateView();
    }

    @Override
    public void updateModels() {

    }

    @Override
    protected void startObserving() {
        ApplicationEventBus.getInstance().addObserver(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingRepairReportRepository(this);
    }

    @Override
    protected void stopObserving() {
        ApplicationEventBus.getInstance().deleteObserver(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingRepairReportRepository(this);
    }

    @Override
    protected void updateView() {
        IPendingRepairReportReadable pendingRepairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingRepairReportReadableRepository().getForId(pendingRepairReportId);
        pendingRepairReportView.update(pendingRepairReport);

        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            pendingRepairReportView.displayResponseOptions();
        } else {
            pendingRepairReportView.hideResposneOptions();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
        if(arg.equals(IPendingRepairReportEvents.ACCEPT_EVENT)) {
            startActivity(new PropertyManagerIntentBuilder().buildDashboard(this));
            finish();
        } else if(arg.equals(IPendingRepairReportEvents.DECLINE_EVENT)){
            finish();
        }
    }
}
