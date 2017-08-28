package com.hms.account.view.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms.common.controllers.interfaces.IExternalController;
import com.hms.devices.view.activities.DeviceListActivity;
import com.hms_app.R;
import com.hms.account.models.interfaces.IAccountReadable;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;

public class LoginActivity extends AppCompatActivity implements MeteorCallback, ICallback<IAccountReadable>{

    private IExternalController _externalController;
    private AlertDialog.Builder _incorrectLoginAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _externalController = new ExternalController();

        initializeIncorrectLoginAlert();
        initializeButtonListeners();
        meteorConnect();
    }


    private void initializeButtonListeners() {
        Button loginBtn = (Button)findViewById(R.id.login_btn_login);
        Button createAccBtn = (Button)findViewById(R.id.login_btn_create_acc);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postLogin();
            }
        });

        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCreateAccount();
            }
        });
    }


    private void initializeIncorrectLoginAlert() {

        _incorrectLoginAlert = new AlertDialog.Builder(this);
        _incorrectLoginAlert.setMessage(R.string.incorrect_login);
        _incorrectLoginAlert.setNegativeButton(R.string.try_again, null);
    }


    private void postLogin() {

        TextView emailTv = (TextView)findViewById(R.id.login_tv_email);
        TextView passwordTv = (TextView)findViewById(R.id.login_tv_password);

        String email = emailTv.getText().toString();
        String password = passwordTv.getText().toString();

        _externalController.getAccountHandler().authenticate(email, password, new ICallback<Boolean>() {

            @Override
            public void callback(CallbackException e, Boolean data) {
                if (data == true) {
                    toDeviceListActivity();
                } else {
                    _incorrectLoginAlert.create();
                    _incorrectLoginAlert.show();
                }
            }
        });
    }

    private void meteorConnect(){

        if(MeteorSingleton.hasInstance()){
            MeteorSingleton.getInstance().setCallback(this);
        }
        else {
            MeteorSingleton.createInstance(getApplicationContext(), "ws://192.168.56.1:3000/websocket");
            MeteorSingleton.getInstance().setCallback(this);
        }


    }

    private void postCreateAccount() {
        Intent createAccIntent = new Intent(this, CreateAccountActivity.class);
        startActivity(createAccIntent);
    }

    private void toDeviceListActivity() {
        Intent deviceListActivity = new Intent(this, DeviceListActivity.class);
        startActivity(deviceListActivity);
    }


    @Override
    public void callback(CallbackException e, IAccountReadable data) {

    }

    @Override
    public void onConnect(boolean b) {
        System.out.println("hello###################");
    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onDataAdded(String s, String s1, String s2) {

    }

    @Override
    public void onDataChanged(String s, String s1, String s2, String s3) {

    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}
