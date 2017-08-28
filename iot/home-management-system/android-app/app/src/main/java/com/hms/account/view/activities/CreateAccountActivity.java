package com.hms.account.view.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hms.account.exceptions.AccountsException;
import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms.common.controllers.interfaces.IExternalController;
import com.hms.devices.view.activities.DeviceListActivity;
import com.hms_app.R;
import com.hms.account.models.interfaces.IAccountReadable;

public class CreateAccountActivity extends AppCompatActivity implements ICallback<IAccountReadable> {

    private IExternalController _externalController;

    private AlertDialog.Builder _incorrectEmailAlert;
    private AlertDialog.Builder _incorrectPasswordAlert;
    private AlertDialog.Builder _blankAlert;

    private EditText _enterEmail;
    private EditText _enterPassword;
    private EditText _passwordVerify;

    private Button register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        _externalController = new ExternalController();

        _enterEmail = (EditText)findViewById(R.id.create_account_et_email);
        _enterPassword = (EditText)findViewById(R.id.create_account_et_password);;
        _passwordVerify = (EditText)findViewById(R.id.create_account_et_verify_password);

        register = (Button)findViewById(R.id.create_account_button_register);

        _incorrectEmailAlert = new AlertDialog.Builder(this);
        _incorrectEmailAlert.setMessage(R.string.incorrect_email);
        _incorrectEmailAlert.setNegativeButton(R.string.try_again, null);

        _incorrectPasswordAlert = new AlertDialog.Builder(this);
        _incorrectPasswordAlert.setMessage(R.string.incorrect_password);
        _incorrectPasswordAlert.setNegativeButton(R.string.try_again, null);

        _blankAlert = new AlertDialog.Builder(this);
        _blankAlert.setMessage(R.string.blank);
        _blankAlert.setNegativeButton(R.string.try_again, null);


        initBtnListeners();
    }

    public void createAccount() {

        String email = _enterEmail.getText().toString();
        String password = _enterPassword.getText().toString();
        String verifyPassword = _passwordVerify.getText().toString();

        if(password.equals(verifyPassword)) {
            try {
                _externalController.getAccountHandler().createAccount(email, password, this);
            } catch (AccountsException e) {
                e.printStackTrace();
            }
        }
        else {
            _incorrectPasswordAlert.show();
        }
    }

    public void toDeviceListActivity(){

        Intent deviceListActivity = new Intent(this, DeviceListActivity.class);
        startActivity(deviceListActivity);

    }



    private void initBtnListeners() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (_enterEmail.getText().toString().equals("") ||
                        _enterPassword.getText().toString().equals("") ||
                        _passwordVerify.getText().toString().equals("")){

                    _blankAlert.create();
                    _blankAlert.show();


                }
                else {
                    createAccount();
                }
            }
        });

    }

    @Override
    public void callback(CallbackException e, IAccountReadable data) {
        System.out.println("Account created for " + data.getEmail());
        toDeviceListActivity();
    }
}
