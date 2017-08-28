package com.appl_maint_mngt.property.views.interfaces;

import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IPropertyView {

    void update(IPropertyReadable property);
    void updatePropertyAppliances(List<IPropertyApplianceReadable> list);
    void propertyApplianceOnItemClickListener(AdapterView.OnItemClickListener listener);
}
