package com.hms.power_sockets.view.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.power_sockets.models.interfaces.IPowerSocketReadable;
import com.hms.devices.model.DeviceModel;
import com.hms.power_sockets.models.PowerSocketModel;
import com.hms.power_sockets.view.util.PowerSocketsAdapter;

import java.util.ArrayList;
import java.util.List;

public class PowerSocketsActivity extends AppCompatActivity implements ICallback<List<IPowerSocketReadable>> {

    private ExternalController _externalController;
    private ArrayList<IPowerSocketReadable> _powerSockets;
    private PowerSocketsAdapter _powerSocketsAdapter;
    private View _addPowerSocketView;
    private IDeviceReadable _device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_sockets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Bundle deviceBundle = getIntent().getExtras();
        String deviceId = deviceBundle.getString("device_id");
        String deviceName= deviceBundle.getString("device_name");
        _device = new DeviceModel(deviceId, deviceName);

        _powerSockets = new ArrayList<>();

        _powerSocketsAdapter = new PowerSocketsAdapter(this, _powerSockets, _device);
        ListView powerSocketsLv = (ListView)findViewById(R.id.power_sockets_lv);
        powerSocketsLv.setAdapter(_powerSocketsAdapter);


        _externalController = new ExternalController();
        _externalController.getPowerSocketHandler().getPowerSockets(_device, this);

        showPowerSocketView();
        initButtonListeners();
    }


    private void initButtonListeners(){

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddSocketView();
            }
        });

        final EditText socketNameEt = (EditText)findViewById(R.id.power_sockets_et_socket_name);
        final EditText socketNumberEt = (EditText)findViewById(R.id.power_sockets_et_socket_number);

        Button addBtn = (Button)_addPowerSocketView.findViewById(R.id.power_sockets_btn_add_socket);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String socketName = socketNameEt.getText().toString();
                String strSocketNumber = socketNumberEt.getText().toString();

                if((socketName.isEmpty()) || (strSocketNumber.isEmpty())) {
                    showDialog("Empty Fields!", "Please fill in all fields");
                }
                else {
                    int socketNumber = Integer.parseInt(strSocketNumber);

                    IPowerSocketReadable powerSocket = new PowerSocketModel(null, socketName, socketNumber, false);
                    _externalController.getPowerSocketHandler().sendPowerSocket(_device, powerSocket, new ICallback<Boolean>() {
                        @Override
                        public void callback(CallbackException e, Boolean data) {
                            if (!data.booleanValue()) {
                                showDialog("Error!", "Failed to add power socket, check your connection and try again.");
                            } else {
                                showPowerSocketView();
                            }
                        }
                    });
                }
            }
        });


        Button cancelBtn = (Button)_addPowerSocketView.findViewById(R.id.power_sockets_btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPowerSocketView();
                socketNameEt.setText("");
                socketNumberEt.setText("");
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


    private void showAddSocketView() {
        ListView powerSocketsLv = (ListView)findViewById(R.id.power_sockets_lv);
        powerSocketsLv.setVisibility(View.GONE);

        _addPowerSocketView = findViewById(R.id.power_sockets_add_view);
        _addPowerSocketView.setVisibility(View.VISIBLE);
    }


    private void showPowerSocketView() {
        ListView powerSocketsLv = (ListView)findViewById(R.id.power_sockets_lv);
        powerSocketsLv.setVisibility(View.VISIBLE);

        _addPowerSocketView = findViewById(R.id.power_sockets_add_view);
        _addPowerSocketView.setVisibility(View.GONE);
    }

    @Override
    public void callback(CallbackException e, List<IPowerSocketReadable> data) {
        _powerSocketsAdapter.clear();
        _powerSocketsAdapter.addAll(data);
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
                _externalController.getPowerSocketHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getPowerSocketHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
