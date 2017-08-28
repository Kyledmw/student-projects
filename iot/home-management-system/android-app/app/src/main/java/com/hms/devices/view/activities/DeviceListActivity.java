package com.hms.devices.view.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.util.callbacks.IDeviceCallback;
import com.hms.common.controllers.ExternalController;
import com.hms.common.controllers.interfaces.IExternalController;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.devices.view.util.DeviceListAdapter;

import java.util.ArrayList;
import java.util.List;

public class DeviceListActivity extends AppCompatActivity
        implements ICallback<List<IDeviceReadable>>, IDeviceCallback {

    private IExternalController _externalController;
    private DeviceListAdapter _deviceListAdapter;
    private List<IDeviceReadable> _deviceList;
    private String _deviceListSubscriptionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _externalController = new ExternalController();
        _deviceList = new ArrayList<>();
        initializeDeviceListAdapter();
        initializeListView();
        initButtonListeners();
        showDeviceList();
        getDevices();
    }


    private void initializeListView() {
        initializeDeviceListAdapter();
        ListView listView = (ListView) findViewById(R.id.device_lv_names);
        listView.setAdapter(_deviceListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _externalController.getDeviceHandler().unRegisterAsCallback();
                IDeviceReadable selectedDevice = _deviceListAdapter.getItem(position);
                toDeviceDashboard(selectedDevice);
            }
        });

    }


    private void showAddDeviceView() {
        View addDeviceView = findViewById(R.id.device_list_view_add_device);
        addDeviceView.setVisibility(View.VISIBLE);

        ListView deviceList = (ListView)findViewById(R.id.device_lv_names);
        deviceList.setVisibility(View.GONE);
    }


    private void showDeviceList() {
        View addDeviceView = findViewById(R.id.device_list_view_add_device);
        addDeviceView.setVisibility(View.GONE);

        ListView deviceList = (ListView)findViewById(R.id.device_lv_names);
        deviceList.setVisibility(View.VISIBLE);
    }


    private void initButtonListeners() {
        FloatingActionButton addFab = (FloatingActionButton)findViewById(R.id.device_list_add_device);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDeviceView();
            }
        });

        final EditText deviceNameEt = (EditText) findViewById(R.id.add_device_et_device_name);
        final EditText deviceKeyEt = (EditText) findViewById(R.id.add_device_et_device_key);

        View addDeviceView = findViewById(R.id.device_list_view_add_device);
        Button cancelBtn = (Button)addDeviceView.findViewById(R.id.add_device_btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeviceList();
                deviceNameEt.setText("");
                deviceKeyEt.setText("");
            }
        });

        Button addBtn = (Button)addDeviceView.findViewById(R.id.add_device_btn_add_device);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deviceName = deviceNameEt.getText().toString();
                String deviceKey = deviceKeyEt.getText().toString();

                if((deviceNameEt.getText().toString().isEmpty())
                        || (deviceKeyEt.getText().toString().isEmpty())) {

                    showDialog("Empty Fields!", "Please fill in all fields");
                }
                else {
                    _externalController.getDeviceHandler().activateDevice(deviceKey, deviceName, new ICallback<Boolean>() {
                        @Override
                        public void callback(CallbackException e, Boolean data) {

                            if (!data.booleanValue()) {
                                showDialog("Device Not Found!", "Failed to add device, details may be incorrect.");
                            } else {
                                showDeviceList();
                            }
                        }
                    });
                }
            }
        });
    }


    private void showDialog(String title, String message) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }


    private void toDeviceDashboard(IDeviceReadable device) {
        Bundle deviceBundle = new Bundle();
        deviceBundle.putString("device_id", device.getId());
        deviceBundle.putString("device_name", device.getName());

        Intent intent = new Intent(this, DeviceDashboardActivity.class);
        intent.putExtras(deviceBundle);

        startActivity(intent);
    }


    private void toLoginActivity() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }


    private void initializeDeviceListAdapter() {
        _deviceListAdapter = new DeviceListAdapter(DeviceListActivity.this, _deviceList, this);
    }

    public void getDevices() {
        _deviceListSubscriptionId = _externalController.getDeviceHandler().getDevices(null, this);
    }


    @Override
    public void callback(CallbackException e, List<IDeviceReadable> data) {
        _deviceList = new ArrayList<IDeviceReadable>(data);
        _deviceListAdapter.clear();
        _deviceListAdapter.addAll(data);
        _deviceListAdapter.notifyDataSetChanged();
    }

    @Override
    public void deviceCallback(IDeviceReadable device) {
        _externalController.getDeviceHandler().unRegisterAsCallback();
        toDeviceDashboard(device);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage(R.string.logout_message);
        alertBuilder.setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _externalController.getAccountHandler().logout();
                _externalController.getDeviceHandler().unRegisterAsCallback();
                toLoginActivity();
            }
        });

        alertBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getDeviceHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
