package com.appl_maint_mngt.property_tenant.views.interfaces;

import android.view.View;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IPropertyTenantDashboardView {
    void setPropertyOnClickListener(View.OnClickListener listener);
    void enablePropertyButton();
    void disablePropertyButton();

    void setMaintenanceScheduleOnClickListener(View.OnClickListener listener);
    void enableMaintenanceScheduleButton();
    void disableMaintenanceScheduleButton();

    void resetView();
}
