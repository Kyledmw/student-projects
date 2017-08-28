package com.appl_maint_mngt.property.views.interfaces;

import android.content.Intent;

import com.appl_maint_mngt.common.views.ACommonActivity;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IPropertyIntentBuilder {

    Intent build(ACommonActivity activity, Long propertyId);
}
