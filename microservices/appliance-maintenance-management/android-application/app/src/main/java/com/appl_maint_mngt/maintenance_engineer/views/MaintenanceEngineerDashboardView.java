package com.appl_maint_mngt.maintenance_engineer.views;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.maintenance_engineer.views.interfaces.IMaintenanceEngineerDashboardView;

/**
 * Created by Kyle on 07/04/2017.
 */

public class MaintenanceEngineerDashboardView implements IMaintenanceEngineerDashboardView {

    private Button repairReportsButton;
    private Button diagnosticRequestsButton;
    private Button pendingRepairReportButton;
    private Button pendingMaintenanceScheduleButton;
    private Button maintenanceScheduleButton;

    public MaintenanceEngineerDashboardView(Activity parent) {
        repairReportsButton = (Button) parent.findViewById(R.id.maint_eng_dashb_button_repair_reports);
        diagnosticRequestsButton = (Button) parent.findViewById(R.id.maint_eng_dashb_button_diag_req);
        pendingRepairReportButton = (Button) parent.findViewById(R.id.maint_eng_dashb_button_pend_rep_reports);
        pendingMaintenanceScheduleButton = (Button) parent.findViewById(R.id.maint_eng_dashb_button_pend_maint_sched);
        maintenanceScheduleButton = (Button) parent.findViewById(R.id.maint_eng_dashb_button_maint_sched);

        resetView();
    }

    @Override
    public void setDiagnosticRequestsOnClickListener(View.OnClickListener listener) {
        diagnosticRequestsButton.setOnClickListener(listener);
    }

    @Override
    public void enableDiagnosticRequestsButton() {
        diagnosticRequestsButton.setEnabled(true);
    }

    @Override
    public void disableDiagnosticRequestsButton() {
        diagnosticRequestsButton.setEnabled(false);
    }

    @Override
    public void setPendingRepairReportsOnClickListener(View.OnClickListener listener) {
        pendingRepairReportButton.setOnClickListener(listener);
    }

    @Override
    public void enablePendingRepairReportsButton() {
        pendingRepairReportButton.setEnabled(true);
    }

    @Override
    public void disablePendingRepairReportsButton() {
        pendingRepairReportButton.setEnabled(false);
    }

    @Override
    public void setRepairReportsOnClickListener(View.OnClickListener listener) {
        repairReportsButton.setOnClickListener(listener);
    }

    @Override
    public void enableRepairReportsButton() {
        repairReportsButton.setEnabled(true);
    }

    @Override
    public void disableRepairReportsButton() {
        repairReportsButton.setEnabled(false);
    }

    @Override
    public void setPendingMaintenanceSchedulingOnClickListener(View.OnClickListener listener) {
        pendingMaintenanceScheduleButton.setOnClickListener(listener);
    }

    @Override
    public void enablePendingMaintenanceSchedulingButton() {
        pendingMaintenanceScheduleButton.setEnabled(true);
    }

    @Override
    public void disablePendingMaintenanceSchedulingButton() {
        pendingMaintenanceScheduleButton.setEnabled(false);
    }

    @Override
    public void setMaintenanceSchedulingOnClickListener(View.OnClickListener listener) {
        maintenanceScheduleButton.setOnClickListener(listener);
    }

    @Override
    public void enableMaintenanceSchedulingButton() {
        maintenanceScheduleButton.setEnabled(true);
    }

    @Override
    public void disableMaintenanceSchedulingButton() {
        maintenanceScheduleButton.setEnabled(false);
    }

    @Override
    public void resetView() {
        disableDiagnosticRequestsButton();
        disablePendingRepairReportsButton();
        disableRepairReportsButton();
        disablePendingMaintenanceSchedulingButton();
        disableMaintenanceSchedulingButton();
    }
}
