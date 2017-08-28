package com.hms.sensors.atmospheric_pressure.view.activities;

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
import com.hms.sensors.atmospheric_pressure.models.interfaces.IAtmosphericPressureReadable;
import com.hms.devices.model.DeviceModel;
import com.hms.sensors.atmospheric_pressure.view.util.AtmosphericPressureAdapter;

import java.util.ArrayList;

public class AtmosphericPressureActivity extends AppCompatActivity implements ICallback<IAtmosphericPressureReadable> {

    private ExternalController _externalController = new ExternalController();
    private ArrayList<IAtmosphericPressureReadable> _atmosphericPressureData;
    private AtmosphericPressureAdapter _atmosphericPressureAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atmospheric_pressure);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle deviceBundle = getIntent().getExtras();

        initAtmosphericPressureListView();
        getAtmosphericPressureData(deviceBundle);
    }


    private void initAtmosphericPressureListView() {
        _atmosphericPressureData = new ArrayList<>();
        _atmosphericPressureAdapter = new AtmosphericPressureAdapter(this, _atmosphericPressureData);

        ListView atmPressureLv = (ListView)findViewById(R.id.atm_pressure_lv);
        atmPressureLv.setAdapter(_atmosphericPressureAdapter);
    }


    private void getAtmosphericPressureData(Bundle deviceBundle) {
        String deviceId = deviceBundle.getString("device_id");
        String deviceName = deviceBundle.getString("device_name");
        _externalController.getAtmosphericPressureHandler().getAtmosphericPressure(new DeviceModel(deviceId, deviceName), this);
    }


    @Override
    public void callback(CallbackException e, IAtmosphericPressureReadable data) {
        _atmosphericPressureAdapter.insert(data, 0);
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
                _externalController.getAtmosphericPressureHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getAtmosphericPressureHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
