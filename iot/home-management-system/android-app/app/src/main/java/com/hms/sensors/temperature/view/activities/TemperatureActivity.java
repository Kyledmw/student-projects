package com.hms.sensors.temperature.view.activities;

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
import com.hms.sensors.temperature.models.interfaces.ITemperatureReadable;
import com.hms.devices.model.DeviceModel;
import com.hms.sensors.temperature.view.util.TemperatureListAdapter;

import java.util.ArrayList;

public class TemperatureActivity extends AppCompatActivity implements ICallback<ITemperatureReadable> {

    private ExternalController _externalController = new ExternalController();
    private TemperatureListAdapter _temperatureListAdapter;
    private ArrayList<ITemperatureReadable> _temperatureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initTemperatureListView();
        Bundle deviceBundle = getIntent().getExtras();
        getTemperatureData(deviceBundle);
    }


    private void initTemperatureListView() {
        _temperatureList = new ArrayList<>();
        _temperatureListAdapter = new TemperatureListAdapter(this, _temperatureList);

        ListView temperature_lv = (ListView)findViewById(R.id.temperature_lv);
        temperature_lv.setAdapter(_temperatureListAdapter);
    }


    public void getTemperatureData(Bundle deviceBundle) {

        String deviceId = deviceBundle.getString("device_id");
        String deviceName = deviceBundle.getString("device_name");
        _externalController.getTemperatureHandler().getTemperature(new DeviceModel(deviceId, deviceName), this);
    }

    @Override
    public void callback(CallbackException e, ITemperatureReadable data) {
        _temperatureListAdapter.insert(data, 0);
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
                _externalController.getTemperatureHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getTemperatureHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
