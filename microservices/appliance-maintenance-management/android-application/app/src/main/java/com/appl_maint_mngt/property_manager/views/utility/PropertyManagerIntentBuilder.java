package com.appl_maint_mngt.property_manager.views.utility;

import android.app.Activity;
import android.content.Intent;

import com.appl_maint_mngt.property_manager.views.PropertyManagerDashboardActivity;
import com.appl_maint_mngt.property_manager.views.interfaces.IPropertyManagerIntentBuilder;

/**
 * Created by Kyle on 12/04/2017.
 */

public class PropertyManagerIntentBuilder implements IPropertyManagerIntentBuilder {
    @Override
    public Intent buildDashboard(Activity activity) {
        return new Intent(activity, PropertyManagerDashboardActivity.class);
    }
}
