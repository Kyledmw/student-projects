package com.appl_maint_mngt.diagnostic_request.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.views.interfaces.ICommonDialog;
import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.maintenance_organisation.views.utility.MaintenanceOrganisationListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Kyle on 10/04/2017.
 */

public class SendDiagnosticRequestsDialog implements ICommonDialog {

    private Activity parent;
    private List<IMaintenanceOrganisationReadable> maintenanceOrganisation;

    private DialogInterface.OnClickListener okListener;

    private List<IMaintenanceOrganisationReadable> selected;

    private AlertDialog dialog;

    public SendDiagnosticRequestsDialog(Activity parent, List<IMaintenanceOrganisationReadable> list) {
        this.parent = parent;
        this.maintenanceOrganisation = list;
        selected = new ArrayList<>();
    }

    @Override
    public void setOnPositiveButtonClickListener(DialogInterface.OnClickListener listener) {
        okListener = listener;
    }

    @Override
    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(parent);
        LayoutInflater inflater = parent.getLayoutInflater();
        View convertView = inflater.inflate(R.layout.dialog_send_diagnostic_requests, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(R.string.maintenance_organisation_action_select_plural);
        alertDialog.setPositiveButton(R.string.common_ok, okListener);

        MaintenanceOrganisationListAdapter listAdapter = new MaintenanceOrganisationListAdapter(parent, maintenanceOrganisation);
        ListView maintOrgLV = (ListView) convertView.findViewById(R.id.dialog_send_diagnostic_requests_listview_maint_org);
        maintOrgLV.setAdapter(listAdapter);

        maintOrgLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IMaintenanceOrganisationReadable item = (IMaintenanceOrganisationReadable) parent.getItemAtPosition(position);
                if(selected.contains(item)) {
                    selected.remove(item);
                    view.setBackgroundColor(Color.WHITE);
                } else {
                    selected.add(item);
                    view.setBackgroundColor(Color.CYAN);
                }
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

    public List<IMaintenanceOrganisationReadable> getSelected() {
        return selected;
    }
}
