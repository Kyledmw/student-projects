package com.hms.devices.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms.common.controllers.interfaces.IExternalController;
import com.hms.alarm_settings.view.activities.AlarmSettingsActivity;
import com.hms.camera.view.activities.CameraActivity;
import com.hms.camera.view.activities.CameraSettingsActivity;
import com.hms.power_sockets.view.activities.PowerSocketsActivity;
import com.hms.sensors.altitude.view.activities.AltitudeActivity;
import com.hms.sensors.atmospheric_pressure.view.activities.AtmosphericPressureActivity;
import com.hms.sensors.motion.view.activities.MotionDetectionActivity;
import com.hms.sensors.settings.view.activities.SensorSettingsActivity;
import com.hms.sensors.temperature.view.activities.TemperatureActivity;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.devices.model.interfaces.IDeviceFeatureReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.devices.model.DeviceModel;
import com.hms.devices.view.util.DashboardAdapter;
import com.hms.common.view.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

//import com.external_data.DummyExternalController;

public class DeviceDashboardActivity extends AppCompatActivity {

    private IExternalController _externalController;
    private IDeviceReadable _device;

    private ImageButton _cameraSettings;
    private ImageButton _alarmSettings;
    private ImageButton _heatingSettings;
    private ImageButton _lightingSettings;
    private ImageButton _contact;
    private Menu _menu;

    private ArrayList<IDeviceFeatureReadable> _deviceFeatureReadables = new ArrayList<>();
    private ArrayList<String> devFeatures = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_dashboard);

        _externalController = new ExternalController();

        Bundle deviceBundle = getIntent().getExtras();
        String deviceId = deviceBundle.getString("device_id");
        String deviceName = deviceBundle.getString("device_name");
        _device = new DeviceModel(deviceId, deviceName);
    }


    public void initDashboardMenu( List<IDeviceFeatureReadable> data){
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        for(IDeviceFeatureReadable devFeature: data) {
            switch(devFeature.getType()) {
                case "Camera":
                    menuItems.add(new MenuItem(R.drawable.menu_item_camera_100, "Camera Feed"));
                    devFeatures.add(devFeature.getType());
                    break;
                case "Temperature_Sensor":
                    menuItems.add(new MenuItem(R.drawable.menu_item_temperature, "Temperature"));
                    devFeatures.add(devFeature.getType());
                    break;
                case "AtmosphericPressure_Sensor":
                    menuItems.add(new MenuItem(R.drawable.menu_item_atm_pressure_100, "Atmospheric Pressure"));
                    devFeatures.add(devFeature.getType());
                    break;
                case "Altitude":
                    menuItems.add(new MenuItem(R.drawable.menu_item_altitude_100, "Altitude"));
                    devFeatures.add(devFeature.getType());
                    break;
                case "Motion_Detection":
                    menuItems.add(new MenuItem(R.drawable.menu_item_motion_detected_100, "Motion Detection"));
                    devFeatures.add(devFeature.getType());
                    break;
                case "Device_Moved":
                    menuItems.add(new MenuItem(R.drawable.menu_item_device_moved_100, "Device Movement"));
                    devFeatures.add(devFeature.getType());
                    break;
                case "Alarm":
                    break;
                case "Light_Detection":
                    break;
                case "Power Sockets":
                    menuItems.add(new MenuItem(R.drawable.menu_item_power_socket_100, "Power Sockets"));
                    devFeatures.add(devFeature.getType());
                    break;
            }

        }

        DashboardAdapter adapter = new DashboardAdapter(this, menuItems);
        ListView menuLv = (ListView)findViewById(R.id.dash_lv);
        menuLv.setAdapter(adapter);

        menuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleMenItemClick(position);
            }
        });
    }


    private void handleMenItemClick(int position) {

        _externalController.getDeviceHandler().unRegisterAsCallback();
        Bundle deviceBundle = getIntent().getExtras();

        switch (devFeatures.get(position)) {
            case "Camera":
                toCameraActivity(deviceBundle);
                break;
            case "Temperature_Sensor":
                toTemperatureActivity(deviceBundle);
                break;
            case "AtmosphericPressure_Sensor":
                toAtmosphericPressureActivity(deviceBundle);
                break;
            case "Altitude":
                toAltitudeActivity(deviceBundle);
                break;
            case "Motion_Detection":
                toMotionDetectionActivity(deviceBundle);
                break;
            case "Device_Moved":
                toDeviceMovedActivity(deviceBundle);
                break;
            case "Alarm":
                break;
            case "Light_Detection":
                break;
            case "Power Sockets":
                toPowerSocketsActivity(deviceBundle);
                break;
        }
    }


    private void toAltitudeActivity(Bundle deviceBundle) {
        Intent altitudeIntent = new Intent(this, AltitudeActivity.class);
        altitudeIntent.putExtras(deviceBundle);
        startActivity(altitudeIntent);
    }

    private void toAtmosphericPressureActivity(Bundle deviceBundle) {
        Intent atmPressureIntent = new Intent(this, AtmosphericPressureActivity.class);
        atmPressureIntent.putExtras(deviceBundle);
        startActivity(atmPressureIntent);
    }

    private void toCameraActivity(Bundle deviceBundle) {
        Intent cameraIntent = new Intent(this, CameraActivity.class);
        cameraIntent.putExtras(deviceBundle);
        startActivity(cameraIntent);
    }

    private void toDeviceMovedActivity(Bundle deviceBundle) {
        Intent devMovedIntent = new Intent(this, DeviceMovedActivity.class);
        devMovedIntent.putExtras(deviceBundle);
        startActivity(devMovedIntent);
    }

    private void toMotionDetectionActivity(Bundle deviceBundle) {
        Intent motionDetectedIntent = new Intent(this, MotionDetectionActivity.class);
        motionDetectedIntent.putExtras(deviceBundle);
        startActivity(motionDetectedIntent);
    }

    private void toPowerSocketsActivity(Bundle deviceBundle) {
        Intent powerSocketsIntent = new Intent(this, PowerSocketsActivity.class);
        powerSocketsIntent.putExtras(deviceBundle);
        startActivity(powerSocketsIntent);
    }

    private void toTemperatureActivity(Bundle deviceBundle) {
        Intent temperatureIntent = new Intent(this, TemperatureActivity.class);
        temperatureIntent.putExtras(deviceBundle);
        startActivity(temperatureIntent);
    }

    private void toCameraSettingsActivity(Bundle deviceBundle) {
        Intent cameraSettingsIntent = new Intent(this, CameraSettingsActivity.class);
        cameraSettingsIntent.putExtras(deviceBundle);
        startActivity(cameraSettingsIntent);
    }

    private void toSensorSettingsActivity(Bundle deviceBundle) {
        Intent sensorSettingsIntent = new Intent(this, SensorSettingsActivity.class);
        sensorSettingsIntent.putExtras(deviceBundle);
        startActivity(sensorSettingsIntent);

    }

    private void toAlarmSettings(Bundle deviceBundle) {
        deviceBundle.putStringArrayList("features", devFeatures);

        Intent alarmSettingsIntent = new Intent(this, AlarmSettingsActivity.class);
        alarmSettingsIntent.putExtras(deviceBundle);
        startActivity(alarmSettingsIntent);
    }


    private void toLoginActivity() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }



    private void prepareDropdownMenu() {
        _menu.findItem(R.id.alarm_settings).setEnabled(false);
        _menu.findItem(R.id.sensor_settings).setEnabled(false);
        _menu.findItem(R.id.camera_settings).setEnabled(false);

        String test = "";
        for(IDeviceFeatureReadable feat : _deviceFeatureReadables) {

            switch (feat.getType()) {
                case "Temperature_Sensor":
                case "Altitude":
                case "AtmosphericPressure_Sensor":
                case "Device_Moved":
                    _menu.findItem(R.id.sensor_settings).setEnabled(true);
                    break;

                case "Alarm":
                    _menu.findItem(R.id.alarm_settings).setEnabled(true);
                    break;

                case "Camera":
                    _menu.findItem(R.id.camera_settings).setEnabled(true);
                    break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        _menu = menu;

        _externalController.getDeviceFeatures().getFeaturesForDevice(_device, new ICallback<List<IDeviceFeatureReadable>>() {
            @Override
            public void callback(CallbackException e, List<IDeviceFeatureReadable> data) {
                getSupportActionBar().setTitle(_device.getName());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                _deviceFeatureReadables.addAll(data);
                initDashboardMenu(data);
                prepareDropdownMenu();
            }
        });


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.camera_settings:
                toCameraSettingsActivity(getIntent().getExtras());
                break;
            case R.id.alarm_settings:
                toAlarmSettings(getIntent().getExtras());
                return true;
            case R.id.sensor_settings:
                toSensorSettingsActivity(getIntent().getExtras());
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                toLoginActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
