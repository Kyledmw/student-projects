package com.hms.devices.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.devices.model.interfaces.IDeviceMovedReadable;
import com.hms.devices.model.DeviceModel;
import com.hms.devices.view.util.DeviceMovedAdapter;

import java.util.ArrayList;

public class DeviceMovedActivity extends AppCompatActivity implements ICallback<IDeviceMovedReadable> {

    private ExternalController _externalController = new ExternalController();
    private ArrayList<IDeviceMovedReadable> _deviceMovedData;
    private DeviceMovedAdapter _deviceMovedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_moved);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle deviceBundle = getIntent().getExtras();
        getDeviceMovedData(deviceBundle);
        initDeviceMovedListView();
    }

    private void initDeviceMovedListView() {
        _deviceMovedData = new ArrayList<>();
        _deviceMovedAdapter = new DeviceMovedAdapter(this, _deviceMovedData);

        ListView deviceMovedListView = (ListView) findViewById(R.id.device_moved_lv);
        deviceMovedListView.setAdapter(_deviceMovedAdapter);
    }

    private void  getDeviceMovedData(Bundle deviceBundle) {
        String deviceId = deviceBundle.getString("device_id");
        String deviceName = deviceBundle.getString("device_name");
        _externalController.getDeviceMovedHandler().getDeviceMoved(new DeviceModel(deviceId, deviceName), this);
    }

    @Override
    public void callback(CallbackException e, IDeviceMovedReadable data) {
      _deviceMovedAdapter.add(data);
    }


    private void toLoginActivity() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
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
                _externalController.getDeviceMovedHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getDeviceMovedHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
