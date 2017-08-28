package com.hms.devices.view.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hms.devices.util.callbacks.IDeviceCallback;
import com.hms.common.controllers.ExternalController;
import com.hms_app.R;
import com.hms.devices.model.interfaces.IDeviceReadable;

import java.util.List;

import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;

/**
 * Created by Kyle on 09/02/2016.
 */
public class DeviceListAdapter extends ArrayAdapter<IDeviceReadable> {

    private ExternalController _externalController;
    private IDeviceCallback _deviceCallback;
    private AlertDialog.Builder _alertDialogBuilder;

    public DeviceListAdapter(Context context, List<IDeviceReadable> devices, IDeviceCallback deviceCallback) {
        super(context, 0, devices);
        _externalController = new ExternalController();
        this._deviceCallback = deviceCallback;
        this._alertDialogBuilder = new AlertDialog.Builder(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final IDeviceReadable device = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.device_adapter_view, parent, false);
        }

        TextView deviceName = (TextView) convertView.findViewById(R.id.device_view_device_name);
        deviceName.setText(device.getName());

        ImageButton deleteBtn = (ImageButton)convertView.findViewById(R.id.device_view_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDeviceReadable device = getItem(position);
                showDeleteAlertDialog(device);
            }
        });


        ImageButton dashboardButton = (ImageButton)convertView.findViewById(R.id.device_view_btn_dashboard);
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _deviceCallback.deviceCallback(device);
            }
        });


        MeteorSingleton.getInstance().call("implementedDeviceFeaturesForDevice", new Object[]{device.getId()}, new ResultListener() {
            @Override
            public void onSuccess(String s) {
                System.out.println("##" + device.getName() + " Features: " + s);
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("## Error getting features for " + device.getName() + s + "\n" + s1 + "\n" + s2);
            }
        });
        return convertView;
    }


    private void showDeleteAlertDialog(final IDeviceReadable device) {
        _alertDialogBuilder.setTitle("Deregister Device");
        _alertDialogBuilder.setMessage("Are you sure you want to deregister " + device.getName() + "?");
        _alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _externalController.getDeviceHandler().deActivateDevice(device, null);
            }
        });

        _alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = _alertDialogBuilder.create();
        alertDialog.show();
    }
}
