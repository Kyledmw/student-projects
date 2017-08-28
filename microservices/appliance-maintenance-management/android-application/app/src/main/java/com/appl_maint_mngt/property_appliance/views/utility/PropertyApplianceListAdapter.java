package com.appl_maint_mngt.property_appliance.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyApplianceListAdapter extends ACommonListAdapter<IPropertyApplianceReadable> {
    public PropertyApplianceListAdapter(@NonNull Context context, @NonNull List<IPropertyApplianceReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IPropertyApplianceReadable propertyAppliance = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_property_appliance);

        TextView nameTV = (TextView) convertView.findViewById(R.id.listitem_property_appliance_name);
        nameTV.setText(propertyAppliance.getName());

        return convertView;
    }
}
