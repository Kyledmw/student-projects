package com.appl_maint_mngt.property_manager.views;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.property_manager.views.interfaces.IPropertyManagerDashboardView;

/**
 * Created by Kyle on 07/04/2017.
 */

public class PropertyManangerDashboardView implements IPropertyManagerDashboardView {

    private Button propertiesButton;
    private Button maintenanceSchedulesButton;
    private Button pendingMaintenanceSchedulesButton;
    private Button pendingRepairReportButton;
    private Button diagnosticRequestsButton;

    public PropertyManangerDashboardView(Activity parent) {
        propertiesButton = (Button) parent.findViewById(R.id.dashb_propmng_button_properties);
        maintenanceSchedulesButton = (Button) parent.findViewById(R.id.dashb_propmng_button_maintenance_schedule);
        pendingMaintenanceSchedulesButton = (Button) parent.findViewById(R.id.dashb_propmng_button_pending_maintenance_scheduling);
        pendingRepairReportButton = (Button) parent.findViewById(R.id.dashb_propmng_button_pending_repair_reports);
        diagnosticRequestsButton = (Button) parent.findViewById(R.id.dashb_propmng_button_diagnostic_requests);

        resetView();
    }

    @Override
    public void setPropertiesOnClickListener(View.OnClickListener listener) {
        propertiesButton.setOnClickListener(listener);
    }

    @Override
    public void enablePropertiesButton() {
        propertiesButton.setEnabled(true);
    }

    @Override
    public void disablePropertiesButton() {
        propertiesButton.setEnabled(false);
    }

    @Override
    public void setMaintenanceScheduleOnClickListener(View.OnClickListener listener) {
        maintenanceSchedulesButton.setOnClickListener(listener);
    }

    @Override
    public void enableMaintenanceScheduleButton() {
        maintenanceSchedulesButton.setEnabled(true);
    }

    @Override
    public void disableMaintenanceScheduleButton() {
        maintenanceSchedulesButton.setEnabled(false);
    }

    @Override
    public void setPendingMaintenanceScheduleOnClickListener(View.OnClickListener listener) {
        pendingMaintenanceSchedulesButton.setOnClickListener(listener);
    }

    @Override
    public void enablePendingMaintenanceScheduleButton() {
        pendingMaintenanceSchedulesButton.setEnabled(true);
    }

    @Override
    public void disablePendingMaintenanceScheduleButton() {
        pendingMaintenanceSchedulesButton.setEnabled(false);
    }

    @Override
    public void setPendingRepairReportsOnClickListener(View.OnClickListener listener) {
        pendingRepairReportButton.setOnClickListener(listener);
    }

    @Override
    public void enablePendingRepairReportButton() {
        pendingRepairReportButton.setEnabled(true);
    }

    @Override
    public void disablePendingRepairReportButton() {
        pendingRepairReportButton.setEnabled(false);
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
    public void resetView() {
        disableDiagnosticRequestsButton();
        disableMaintenanceScheduleButton();
        disablePendingMaintenanceScheduleButton();
        disablePendingRepairReportButton();
        disablePropertiesButton();
    }
}
