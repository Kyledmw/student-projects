package com.appl_maint_mngt.pending_repair_report.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.common.errors.LoggingErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.utility.PendingRepairReportRetriever;
import com.appl_maint_mngt.pending_repair_report.views.interfaces.IPendingRepairReportListView;
import com.appl_maint_mngt.pending_repair_report.views.utility.PendingRepairReportIntentBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PendingRepairReportListActivity extends ACommonActivity {

    private IPendingRepairReportListView pendingReportListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_repair_report_list);

        pendingReportListView = new PendingRepairReportListView(this);
        pendingReportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IPendingRepairReportReadable pendingRepairReport = (IPendingRepairReportReadable) parent.getItemAtPosition(position);
                startActivity(new PendingRepairReportIntentBuilder().build(PendingRepairReportListActivity.this, pendingRepairReport.getId()));
            }
        });

        updateView();
    }

    @Override
    public void updateModels() {
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
            IntegrationController.getInstance().getControllerFactory().createPendingRepairReportController().getForEngineer(account.getId(), new LoggingErrorCallback());
        }
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingRepairReportRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingRepairReportRepository(this);
    }

    @Override
    protected void updateView() {
        if(pendingReportListView == null) return;
        List<IPendingRepairReportReadable> list = new ArrayList<>();
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();

        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            list = new PendingRepairReportRetriever().retrievePending();
        } else if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
            list = new PendingRepairReportRetriever().retrievePendingAndDeclined();
        }
        pendingReportListView.update(list);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
