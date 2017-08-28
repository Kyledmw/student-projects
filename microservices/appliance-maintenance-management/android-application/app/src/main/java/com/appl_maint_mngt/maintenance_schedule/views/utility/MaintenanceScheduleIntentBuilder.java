package com.appl_maint_mngt.maintenance_schedule.views.utility;

import android.app.Activity;
import android.content.Intent;

import com.appl_maint_mngt.maintenance_schedule.views.MaintenanceScheduleListActivity;
import com.appl_maint_mngt.maintenance_schedule.views.interfaces.IMaintenanceScheduleIntentBuilder;

/**
 * Created by Kyle on 14/04/2017.
 */
public class MaintenanceScheduleIntentBuilder implements IMaintenanceScheduleIntentBuilder {
    @Override
    public Intent build(Activity activity) {
        return new Intent(activity, MaintenanceScheduleListActivity.class);
    }
}
