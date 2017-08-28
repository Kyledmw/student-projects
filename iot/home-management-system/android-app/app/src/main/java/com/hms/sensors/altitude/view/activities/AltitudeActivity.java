package com.hms.sensors.altitude.view.activities;

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
import com.hms.sensors.altitude.models.interfaces.IAltitudeReadable;
import com.hms.devices.model.DeviceModel;
import com.hms.sensors.altitude.view.util.AltitudeAdapter;

import java.util.ArrayList;

public class AltitudeActivity extends AppCompatActivity implements ICallback<IAltitudeReadable> {

    private ExternalController _externalController = new ExternalController();
    private AltitudeAdapter _altitudeAdapter;
    private ArrayList<IAltitudeReadable> _altitudeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altitude);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle deviceBundle = getIntent().getExtras();
        getAltitudeData(deviceBundle);

        initAltitudeListView();
    }


    private void initAltitudeListView() {
        _altitudeData = new ArrayList<>();
        _altitudeAdapter = new AltitudeAdapter(this, _altitudeData);

        ListView altitudeListView = (ListView) findViewById(R.id.altitude_lv);
        altitudeListView.setAdapter(_altitudeAdapter);
    }

    public void getAltitudeData(Bundle deviceBundle) {
        String deviceId = deviceBundle.getString("device_id");
        String deviceName = deviceBundle.getString("device_name");
        _externalController.getAltitudeHandler().getAltitude(new DeviceModel(deviceId, deviceName), this);
    }

    @Override
    public void callback(CallbackException e, IAltitudeReadable data) {
       _altitudeAdapter.insert(data, 0);
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
                _externalController.getAltitudeHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getAltitudeHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
