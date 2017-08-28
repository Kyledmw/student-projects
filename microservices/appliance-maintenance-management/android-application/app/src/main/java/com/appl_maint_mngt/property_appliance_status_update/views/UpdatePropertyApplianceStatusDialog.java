package com.appl_maint_mngt.property_appliance_status_update.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.appliance_status.views.utility.ApplianceStatusListAdapter;
import com.appl_maint_mngt.property_appliance_status_update.views.interfaces.IUpdatePropertyApplianceStatusDialog;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class UpdatePropertyApplianceStatusDialog implements IUpdatePropertyApplianceStatusDialog {

    private Activity parent;
    private List<IApplianceStatusReadable> statuses;
    private DialogInterface.OnClickListener okListener;
    private IApplianceStatusReadable selectedStatus;

    private AlertDialog dialog;

    public UpdatePropertyApplianceStatusDialog(Activity parent, List<IApplianceStatusReadable> statuses) {
        this.parent = parent;
        this.statuses = statuses;
        this.selectedStatus = statuses.get(0);
    }

    @Override
    public void setOnPositiveButtonClickListener(DialogInterface.OnClickListener listener) {
        okListener = listener;
    }

    @Override
    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(parent);
        LayoutInflater inflater = parent.getLayoutInflater();
        View convertView = inflater.inflate(R.layout.dialog_update_property_appliance_status, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(R.string.appliance_status_label_update);
        alertDialog.setPositiveButton(R.string.common_ok, okListener);

        ApplianceStatusListAdapter listAdapter = new ApplianceStatusListAdapter(convertView.getContext(), statuses);
        ListView applianceStatusLV = (ListView) convertView.findViewById(R.id.dialog_property_appliance_status_update_listview_statuses);
        final TextView selectedStatusTV = (TextView) convertView.findViewById(R.id.dialog_property_appliance_status_update_textview_current);
        selectedStatusTV.setText(selectedStatus.getType().toString());
        applianceStatusLV.setAdapter(listAdapter);
        applianceStatusLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStatus = (IApplianceStatusReadable) parent.getItemAtPosition(position);
                selectedStatusTV.setText(selectedStatus.getType().toString());
            }
        });
        dialog = alertDialog.create();

    }

    @Override
    public void show() {
        dialog.show();
    }

    @Override
    public void close() {
        dialog.cancel();
    }

    @Override
    public IApplianceStatusReadable getSelected() {
        return selectedStatus;
    }
}
