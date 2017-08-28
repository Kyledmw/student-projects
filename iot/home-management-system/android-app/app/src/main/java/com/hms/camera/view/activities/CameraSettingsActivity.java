package com.hms.camera.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.camera.models.CameraSettingsModel;
import com.hms.devices.model.DeviceModel;

public class CameraSettingsActivity extends AppCompatActivity implements ICallback<CameraSettingsModel> {

    private ExternalController _externalController;
    private IDeviceReadable _device;
    private CameraSettingsModel _cameraSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _externalController = new ExternalController();

        _device = new DeviceModel(
                getIntent().getStringExtra("device_id"),
                getIntent().getStringExtra("device_name"));


        _cameraSettings = new CameraSettingsModel("", false, false);
        getCameraSettings();
        initCheckListeners();
    }


    private void getCameraSettings() {
        _externalController.getCameraSettingsHandler().getCameraSettings(_device, this);
    }


    private void  initCheckListeners() {
        CheckBox cameraDataCheck = (CheckBox)findViewById(R.id.settings_cbox_receive_camera_data);
        CheckBox recordingCheck = (CheckBox)findViewById(R.id.settings_cbox_recording);

        cameraDataCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _cameraSettings.setPowerSwitch(isChecked);
                _externalController.getCameraSettingsHandler().sendCameraSettings(_device, _cameraSettings, null);
            }
        });

        recordingCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _cameraSettings.setRecordingSwitch(isChecked);
                _externalController.getCameraSettingsHandler().sendCameraSettings(_device, _cameraSettings, null);
            }
        });
    }


    private void displaySettingsState() {
        CheckBox cameraDataCheck = (CheckBox)findViewById(R.id.settings_cbox_receive_camera_data);
        CheckBox recordingCheck = (CheckBox)findViewById(R.id.settings_cbox_recording);

        cameraDataCheck.setChecked(_cameraSettings.getPowerSwitch());
        recordingCheck.setChecked(_cameraSettings.getRecording());
    }


    @Override
    public void callback(CallbackException e, CameraSettingsModel data) {
        _cameraSettings = data;
        displaySettingsState();
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
                _externalController.getCameraSettingsHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getCameraSettingsHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
