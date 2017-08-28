package com.appl_maint_mngt.property.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.address.utility.AddressDisplayUtility;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyListAdapter extends ACommonListAdapter<IPropertyReadable> {
    public PropertyListAdapter(@NonNull Context context, @NonNull List<IPropertyReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IPropertyReadable property = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_property);

        TextView propertyNameTv = (TextView) convertView.findViewById(R.id.listitem_property_name);
        propertyNameTv.setText(new AddressDisplayUtility().singleAddressLine(property.getAddress()));

        return convertView;
    }
}
