package com.hms.sensors.settings.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.devices.model.DeviceModel;
import com.hms.sensors.settings.models.SensorSettingsModel;

public class SensorSettingsActivity extends AppCompatActivity implements ICallback<SensorSettingsModel> {

    private ExternalController _externalController;
    private IDeviceReadable _device;
    private SensorSettingsModel _sensorSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _externalController = new ExternalController();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _externalController.getSensorSettingsHandler().unRegisterAsCallback();
                onBackPressed();
            }
        });


        _device = new DeviceModel(
                getIntent().getStringExtra("device_id"),
                getIntent().getStringExtra("device_name"));


        getSensorSettings();
        initCheckListener();
    }


    private void getSensorSettings() {
        _externalController.getSensorSettingsHandler().getSensorSettings(_device, this);
    }



    private void initCheckListener() {

        CheckBox sensorDataCheckBox = (CheckBox)findViewById(R.id.settings_cbox_receive_sensor_data);
        final TextView sensorPowerTv = (TextView)findViewById(R.id.settings_receive_sensor_tv_enabled);

        sensorDataCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sensorPowerTv.setText(getResources().getString(R.string.sensors_activated));
                    _sensorSettings.setPowerSwitch(isChecked);
                    _externalController.getSensorSettingsHandler().setSensorSettings(_device, _sensorSettings, null);
                } else {
                    sensorPowerTv.setText(getResources().getString(R.string.sensors_deactivated));
                    _sensorSettings = new SensorSettingsModel(isChecked);
                    _externalController.getSensorSettingsHandler().setSensorSettings(_device, _sensorSettings, null);
                }
            }
        });

    }


    private void displaySettingsState(SensorSettingsModel sensorSettings) {
        CheckBox sensorDataChecked = (CheckBox)findViewById(R.id.settings_cbox_receive_sensor_data);
        sensorDataChecked.setChecked(sensorSettings.getPowerSwitch());
    }



    @Override
    public void callback(CallbackException e, SensorSettingsModel data) {
        _sensorSettings = data;
        displaySettingsState(_sensorSettings);
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
                _externalController.getSensorSettingsHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getSensorSettingsHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
