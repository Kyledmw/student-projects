package com.appl_maint_mngt.appliance_status.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.common.views.ACommonListAdapter;

import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public class ApplianceStatusListAdapter extends ACommonListAdapter<IApplianceStatusReadable> {

    public ApplianceStatusListAdapter(@NonNull Context context, @NonNull List<IApplianceStatusReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IApplianceStatusReadable applianceStatus = (IApplianceStatusReadable) getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_appliance_status);

        TextView typeTV = (TextView) convertView.findViewById(R.id.listitem_appliance_status_type);
        typeTV.setText(applianceStatus.getType().toString());

        return convertView;
    }
}
