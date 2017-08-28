package com.hms.sensors.motion.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.sensors.motion.model.interfaces.IMotionDetectionReadable;
import com.hms.devices.model.DeviceModel;
import com.hms.sensors.motion.view.util.MotionDetectionAdapter;

import java.util.ArrayList;

public class MotionDetectionActivity extends AppCompatActivity implements ICallback<IMotionDetectionReadable> {

    private ExternalController _externalController = new ExternalController();
    private ArrayList<IMotionDetectionReadable> _motionDetectionData;
    private MotionDetectionAdapter _moionDetectionAdapter;
    private ImageView _cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_dection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*View cameraView = findViewById(R.id.motion_detection_camera_view);
        _cameraView = (ImageView)cameraView.findViewById(R.id.camera_data_iv);*/

        Bundle deviceBundle = getIntent().getExtras();
        initMotionDetectionListView();
        getMotionDetectedData(deviceBundle);
    }


    private void initMotionDetectionListView() {
        ListView listView = (ListView)findViewById(R.id.motion_detection_lv);
        _motionDetectionData = new ArrayList<>();
        _moionDetectionAdapter = new MotionDetectionAdapter(this, _motionDetectionData);
        listView.setAdapter(_moionDetectionAdapter);
    }


    public void getMotionDetectedData(Bundle deviceBundle) {
        String deviceId = deviceBundle.getString("device_id");
        String deviceName = deviceBundle.getString("device_name");

        _externalController.getMotionDetectionHandler().getMotionDetected(new DeviceModel(deviceId, deviceName), this);
    }

    @Override
    public void callback(CallbackException e, IMotionDetectionReadable data) {
        System.out.println("Callback------------------------------------");
        _moionDetectionAdapter.add(data);
        /*ICameraReadable cameraData = data.getCameraData();
        if(cameraData != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(cameraData.getImageBytes(), 0, cameraData.getImageBytes().length);
            _cameraView.setImageBitmap(bmp);
        }*/
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
                _externalController.getMotionDetectionHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getMotionDetectionHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
