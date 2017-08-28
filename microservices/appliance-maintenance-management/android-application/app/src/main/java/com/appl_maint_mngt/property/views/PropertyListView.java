package com.appl_maint_mngt.property.views;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;
import com.appl_maint_mngt.property.views.interfaces.IPropertyListView;
import com.appl_maint_mngt.property.views.utility.PropertyListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyListView implements IPropertyListView {

    private ListView propertyLV;
    private PropertyListAdapter propertyListAdapter;

    public PropertyListView(Activity parent){
        propertyLV = (ListView) parent.findViewById(R.id.property_list_items);
        propertyListAdapter = new PropertyListAdapter(parent, new ArrayList<IPropertyReadable>());
        propertyLV.setAdapter(propertyListAdapter);
    }

    @Override
    public void update(List<IPropertyReadable> properties) {
        propertyListAdapter.clear();
        propertyListAdapter.addAll(properties);
        propertyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPropertyOnClickListener(AdapterView.OnItemClickListener listener) {
        propertyLV.setOnItemClickListener(listener);
    }
}
