package com.hms.power_sockets.view.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms_app.R;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.power_sockets.models.interfaces.IPowerSocketReadable;

import java.util.List;

;

/**
 * Created by alan on 03/04/16.
 */
public class PowerSocketsAdapter extends ArrayAdapter<IPowerSocketReadable>{

    private IDeviceReadable _device;
    private ExternalController _externalController;
    private AlertDialog.Builder _alertDialogBuilder;

    public PowerSocketsAdapter(Context context, List<IPowerSocketReadable> powerSocketData, IDeviceReadable device) {
        super(context, 0, powerSocketData);
        this._device = device;
        this._externalController = new ExternalController();
        this._alertDialogBuilder = new AlertDialog.Builder(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        IPowerSocketReadable powerSocketReading = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.power_sockets_adapter_view, parent, false);
        }


        ImageView sensorDataIcon = (ImageView)convertView.findViewById(R.id.power_sockets_view_icon);
        TextView socketNameTv = (TextView)convertView.findViewById(R.id.power_sockets_view_name_tv);
        TextView socketNumberTv = (TextView)convertView.findViewById(R.id.power_sockets_view_number_tv);

        SwitchCompat powerSwitch = (SwitchCompat) convertView.findViewById(R.id.power_sockets_view_switch);
        ImageButton deleteSocketBtn = (ImageButton)convertView.findViewById(R.id.power_sockets_view_delete);

        String strPowerSockets = powerSocketReading.getName();
        final boolean poweredOn = powerSocketReading.getPowerSwitch();

        sensorDataIcon.setImageResource(R.drawable.menu_item_power_socket);
        socketNameTv.setText(strPowerSockets);
        socketNumberTv.setText("Socket Number: " + Integer.toString(powerSocketReading.getNumber()));


        deleteSocketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPowerSocketReadable powerSocket = getItem(position);
                showDeleteAlertDialog(powerSocket);
            }
        });

        powerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                IPowerSocketReadable powerSocket = getItem(position);
                powerSocket.setSwitchState(isChecked);

                System.out.println("----->" + isChecked + position + "<-----");

                _externalController.getPowerSocketHandler().sendPowerSocket(_device, powerSocket, new ICallback<Boolean>() {

                    @Override
                    public void callback(CallbackException e, Boolean data) {
                        System.out.println("#### from handler: " + data.toString() + "###");
                    }
                });
            }
        });

        powerSwitch.setChecked(poweredOn);

        return convertView;
    }


    private void showDeleteAlertDialog(final IPowerSocketReadable powerSocket) {
        _alertDialogBuilder.setTitle("Delete Socket");
        _alertDialogBuilder.setMessage("Are you sure you want to delete " + powerSocket.getName() + "?");
        _alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _externalController.getPowerSocketHandler().deletePowerSocket(_device, powerSocket, new ICallback<Boolean>() {
                    @Override
                    public void callback(CallbackException e, Boolean data) {
                        System.out.println("#### from handler: " + data.toString() + "###");
                    }
                });
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
