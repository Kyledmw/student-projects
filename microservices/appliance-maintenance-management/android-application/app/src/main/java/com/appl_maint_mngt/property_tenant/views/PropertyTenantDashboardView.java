package com.appl_maint_mngt.property_tenant.views;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.property_tenant.views.interfaces.IPropertyTenantDashboardView;

/**
 * Created by Kyle on 07/04/2017.
 */

public class PropertyTenantDashboardView implements IPropertyTenantDashboardView {

    private Button propertyButton;
    private Button maintenanceScheduleButton;

    public PropertyTenantDashboardView(Activity parent) {
        propertyButton = (Button) parent.findViewById(R.id.dashb_proptnt_button_property);
        maintenanceScheduleButton = (Button) parent.findViewById(R.id.dashb_proptnt_button_maint_sched);
        resetView();
    }

    @Override
    public void setPropertyOnClickListener(View.OnClickListener listener) {
        propertyButton.setOnClickListener(listener);
    }

    @Override
    public void enablePropertyButton() {
        propertyButton.setEnabled(true);
    }

    @Override
    public void disablePropertyButton() {
        propertyButton.setEnabled(false);
    }

    @Override
    public void setMaintenanceScheduleOnClickListener(View.OnClickListener listener) {
        maintenanceScheduleButton.setOnClickListener(listener);
    }

    @Override
    public void enableMaintenanceScheduleButton() {
        maintenanceScheduleButton.setEnabled(true);
    }

    @Override
    public void disableMaintenanceScheduleButton() {
        maintenanceScheduleButton.setEnabled(false);
    }

    @Override
    public void resetView() {
        disablePropertyButton();
        disableMaintenanceScheduleButton();
    }
}
