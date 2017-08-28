package com.appl_maint_mngt.maintenance_organisation.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;

import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public class MaintenanceOrganisationListAdapter extends ACommonListAdapter<IMaintenanceOrganisationReadable> {
    public MaintenanceOrganisationListAdapter(@NonNull Context context, @NonNull List<IMaintenanceOrganisationReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IMaintenanceOrganisationReadable maintenanceOrganisation = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_maintenance_organisation);

        TextView nameTV = (TextView) convertView.findViewById(R.id.listitem_maintenance_organisation_name);
        nameTV.setText(maintenanceOrganisation.getName());

        return convertView;
    }
}
