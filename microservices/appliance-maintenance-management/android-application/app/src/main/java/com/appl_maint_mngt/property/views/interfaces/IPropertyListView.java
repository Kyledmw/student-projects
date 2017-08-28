package com.appl_maint_mngt.property.views.interfaces;

import android.widget.AdapterView;

import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IPropertyListView {

    void update(List<IPropertyReadable> properties);
    void setPropertyOnClickListener(AdapterView.OnItemClickListener listener);
}
