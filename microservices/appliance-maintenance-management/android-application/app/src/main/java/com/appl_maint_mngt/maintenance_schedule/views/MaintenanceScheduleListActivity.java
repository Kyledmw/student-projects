package com.appl_maint_mngt.maintenance_schedule.views;

import android.os.Bundle;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance_schedule.views.interfaces.IMaintenanceScheduleListView;

import java.util.Observable;

public class MaintenanceScheduleListActivity extends ACommonActivity {

    private IMaintenanceScheduleListView maintenanceScheduleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_schedule_list);

        maintenanceScheduleListView = new MaintenanceScheduleListView(this);
        updateView();
    }

    @Override
    public void updateModels() {
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeMaintenanceScheduleRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveMaintenanceScheduleRepository(this);
    }

    @Override
    protected void updateView() {
        maintenanceScheduleListView.update(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceScheduleReadableRepository().getForStatus(ScheduleStatus.NORMAL));
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
