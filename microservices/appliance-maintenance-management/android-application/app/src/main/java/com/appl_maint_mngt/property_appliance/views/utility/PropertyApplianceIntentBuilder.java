package com.appl_maint_mngt.property_appliance.views.utility;

import android.content.Intent;

import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.property_appliance.views.PropertyApplianceActivity;
import com.appl_maint_mngt.property_appliance.views.constants.IPropertyApplianceViewConstants;
import com.appl_maint_mngt.property_appliance.views.interfaces.IPropertyApplianceIntentBuilder;

/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyApplianceIntentBuilder implements IPropertyApplianceIntentBuilder {
    @Override
    public Intent build(ACommonActivity activity, Long propertyApplianceId) {
        Intent intent = new Intent(activity, PropertyApplianceActivity.class);
        intent.putExtra(IPropertyApplianceViewConstants.ID_KEY, propertyApplianceId);
        return intent;
    }
}
