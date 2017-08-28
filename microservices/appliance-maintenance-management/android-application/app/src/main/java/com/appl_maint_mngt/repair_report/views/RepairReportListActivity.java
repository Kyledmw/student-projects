package com.appl_maint_mngt.repair_report.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.views.interfaces.IRepairReportListView;
import com.appl_maint_mngt.repair_report.views.utility.RepairReportIntentBuilder;

import java.util.Observable;

public class RepairReportListActivity extends ACommonActivity {

    private IRepairReportListView repairReportListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_report_list);

        repairReportListView = new RepairReportListView(this);
        repairReportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IRepairReportReadable repairReport = (IRepairReportReadable) parent.getItemAtPosition(position);
                startActivity(new RepairReportIntentBuilder().build(RepairReportListActivity.this, repairReport.getId()));
            }
        });
        updateView();
    }

    @Override
    public void updateModels() {

    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeRepairReportRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveRepairReportRepository(this);
    }

    @Override
    protected void updateView() {
        if(repairReportListView == null);
        repairReportListView.update(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getRepairReportReadableRepository().getAll());
    }

    @Override
    public void update(Observable o, Object arg) {
        updateView();
    }
}
