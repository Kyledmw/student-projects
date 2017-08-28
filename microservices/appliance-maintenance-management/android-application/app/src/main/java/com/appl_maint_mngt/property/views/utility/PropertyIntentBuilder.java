package com.appl_maint_mngt.property.views.utility;

import android.content.Intent;

import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.property.views.PropertyActivity;
import com.appl_maint_mngt.property.views.constants.IPropertyViewConstants;
import com.appl_maint_mngt.property.views.interfaces.IPropertyIntentBuilder;

/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyIntentBuilder implements IPropertyIntentBuilder {
    @Override
    public Intent build(ACommonActivity activity, Long propertyId) {
        Intent intent = new Intent(activity, PropertyActivity.class);
        intent.putExtra(IPropertyViewConstants.ID_KEY, propertyId);
        return intent;
    }
}
