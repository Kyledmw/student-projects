package com.hms.camera.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms.common.controllers.interfaces.IExternalController;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.camera.models.interfaces.ICameraReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.devices.model.DeviceModel;

public class CameraActivity extends AppCompatActivity implements ICallback<ICameraReadable> {

    private IExternalController _externalController;
    private ImageView _cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _externalController = new ExternalController();
        View cameraView = findViewById(R.id.camera_camera_view);
        _cameraView = (ImageView)cameraView.findViewById(R.id.camera_data_iv);

        getCameraData(getIntent().getExtras());
    }

    private void getCameraData(Bundle deviceBundle) {
        String deviceId = deviceBundle.getString("device_id");
        String deviceName = deviceBundle.getString("device_name");
        IDeviceReadable device = new DeviceModel(deviceId, deviceName);
        _externalController.getCameraDataHandler().getCameraData(device, this);
    }


    @Override
    public void callback(CallbackException e, ICameraReadable data) {
        Bitmap bmp = BitmapFactory.decodeByteArray(data.getImageBytes(), 0, data.getImageBytes().length);
        _cameraView.setImageBitmap(bmp);
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
                _externalController.getCameraDataHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getCameraDataHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
