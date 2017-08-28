package com.appl_maint_mngt.pending_maintenance_scheduling.views.utility;

import android.app.Activity;
import android.content.Intent;

import com.appl_maint_mngt.pending_maintenance_scheduling.views.PendingMaintenanceSchedulingListActivity;
import com.appl_maint_mngt.pending_maintenance_scheduling.views.interfaces.IPendingMaintenanceSchedulingIntentBuilder;

/**
 * Created by Kyle on 15/04/2017.
 */

public class PendingMaintenanceSchedulingIntentBuilder implements IPendingMaintenanceSchedulingIntentBuilder {
    @Override
    public Intent buildList(Activity activity) {
        return new Intent(activity, PendingMaintenanceSchedulingListActivity.class);
    }
}
