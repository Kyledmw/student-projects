package com.appl_maint_mngt.maintenance_engineer.views.interfaces;

import android.view.View;

/**
 * Created by Kyle on 07/04/2017.
 */
public interface IMaintenanceEngineerDashboardView {

    void setDiagnosticRequestsOnClickListener(View.OnClickListener listener);
    void enableDiagnosticRequestsButton();
    void disableDiagnosticRequestsButton();

    void setPendingRepairReportsOnClickListener(View.OnClickListener listener);
    void enablePendingRepairReportsButton();
    void disablePendingRepairReportsButton();

    void setRepairReportsOnClickListener(View.OnClickListener listener);
    void enableRepairReportsButton();
    void disableRepairReportsButton();

    void setPendingMaintenanceSchedulingOnClickListener(View.OnClickListener listener);
    void enablePendingMaintenanceSchedulingButton();
    void disablePendingMaintenanceSchedulingButton();

    void setMaintenanceSchedulingOnClickListener(View.OnClickListener listener);
    void enableMaintenanceSchedulingButton();
    void disableMaintenanceSchedulingButton();

    void resetView();
}
