package com.appl_maint_mngt.property_manager.views.interfaces;

import android.view.View;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IPropertyManagerDashboardView {
    void setPropertiesOnClickListener(View.OnClickListener listener);
    void enablePropertiesButton();
    void disablePropertiesButton();

    void setMaintenanceScheduleOnClickListener(View.OnClickListener listener);
    void enableMaintenanceScheduleButton();
    void disableMaintenanceScheduleButton();

    void setPendingMaintenanceScheduleOnClickListener(View.OnClickListener listener);
    void enablePendingMaintenanceScheduleButton();
    void disablePendingMaintenanceScheduleButton();

    void setPendingRepairReportsOnClickListener(View.OnClickListener listener);
    void enablePendingRepairReportButton();
    void disablePendingRepairReportButton();

    void setDiagnosticRequestsOnClickListener(View.OnClickListener listener);
    void enableDiagnosticRequestsButton();
    void disableDiagnosticRequestsButton();

    void resetView();
}
