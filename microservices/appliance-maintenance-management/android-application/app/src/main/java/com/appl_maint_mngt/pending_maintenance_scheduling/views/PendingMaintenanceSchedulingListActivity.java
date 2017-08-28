package com.appl_maint_mngt.pending_maintenance_scheduling.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.utility.PendingMaintenanceSchedulingListFilter;
import com.appl_maint_mngt.pending_maintenance_scheduling.views.interfaces.IPendingMaintenanceSchedulingListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PendingMaintenanceSchedulingListActivity extends ACommonActivity {

    private IPendingMaintenanceSchedulingListView pendingMaintenanceSchedulingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_maintenance_scheduling_list);

        pendingMaintenanceSchedulingListView = new PendingMaintenanceSchedulingListView(this);
        pendingMaintenanceSchedulingListView.setOnItemClickLsitener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IPendingMaintenanceScheduleReadable pendingMaintenanceSchedule = (IPendingMaintenanceScheduleReadable) parent.getItemAtPosition(position);
                PendingMaintenanceSchedulingResponseDialog dialog = new PendingMaintenanceSchedulingResponseDialog(PendingMaintenanceSchedulingListActivity.this, pendingMaintenanceSchedule);
                dialog.create();
                dialog.show();
            }
        });
        updateView();
    }

    @Override
    public void updateModels() {
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePendingMaintenanceSchedulingRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePendingMaintenanceSchedulingRepository(this);
    }

    @Override
    protected void updateView() {
        if(pendingMaintenanceSchedulingListView == null) return;
        List<IPendingMaintenanceScheduleReadable> pendingMaintenanceSchedules = new ArrayList<>();
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.MAINTENANCE_ENGINEER)) {
            pendingMaintenanceSchedules = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingMaintenanceSchedulingReadableRepository().getForScheduler(SchedulerType.PROPERTY_REPRESENTITIVE);
            pendingMaintenanceSchedules = new PendingMaintenanceSchedulingListFilter().filterOnScheduleStatus(pendingMaintenanceSchedules, ScheduleStatus.PENDING);
        } else if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            pendingMaintenanceSchedules = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingMaintenanceSchedulingReadableRepository().getForScheduler(SchedulerType.ENGINEER_REPRESENTITIVE);
            pendingMaintenanceSchedules = new PendingMaintenanceSchedulingListFilter().filterOnScheduleStatus(pendingMaintenanceSchedules, ScheduleStatus.PENDING);
        }
        pendingMaintenanceSchedulingListView.update(pendingMaintenanceSchedules);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
