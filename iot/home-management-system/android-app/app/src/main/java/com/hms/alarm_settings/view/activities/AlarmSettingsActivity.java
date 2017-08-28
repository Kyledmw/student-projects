package com.hms.alarm_settings.view.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.common.controllers.ExternalController;
import com.hms_app.R;
import com.hms.account.view.activities.LoginActivity;
import com.hms.data_triggers.models.interfaces.IDataTriggerReadable;
import com.hms.devices.model.interfaces.IDeviceFeatureReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.alarm_settings.models.AlarmSettingModel;
import com.hms.data_triggers.models.DataTriggersModel;
import com.hms.devices.model.DeviceModel;

import java.util.ArrayList;
import java.util.List;

public class AlarmSettingsActivity extends AppCompatActivity implements ICallback<AlarmSettingModel>{

    final private int MAX_DURATION = 300;
    private ExternalController _externalController;
    private IDeviceReadable _device;
    private int _alarmDuration;
    private AlarmSettingModel _alarmSettings;
    private ArrayList<IDeviceFeatureReadable> _features;
    private boolean initUI = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _device = new DeviceModel(
                getIntent().getStringExtra("device_id"),
                getIntent().getStringExtra("device_name"));


        _externalController = new ExternalController();
        _externalController.getAlarmSettingsHandler().getDeviceFeatures(_device, new ICallback<List<IDeviceFeatureReadable>>() {
            @Override
            public void callback(CallbackException e, List<IDeviceFeatureReadable> data) {
                _features = new ArrayList<IDeviceFeatureReadable>();
                _features.addAll(data);
                initFeatureSpinner();
            }
        });

        _alarmDuration = 0;


        getAlarmSettings();
        initNumberPicker();
        initCheckListeners();
        initButtonListeners();
    }


    private void getAlarmSettings() {
        _externalController.getAlarmSettingsHandler().getAlarmSettings(_device, this);
    }


    private void initFeatureSpinner() {

        Spinner spinner = (Spinner)findViewById(R.id.triggers_spinner_device_feature);
        ArrayList<String> featureTypes = new ArrayList<>();

        for(IDeviceFeatureReadable feat : _features ) {
            featureTypes.add(feat.getType());
        }

        ArrayAdapter<String> deviceFeatures = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , featureTypes);

        spinner.setAdapter(deviceFeatures);
    }



    private void initCheckListeners() {
        CheckBox alarmCheckBox = (CheckBox)findViewById(R.id.settings_cbox_enable_alarm);
        CheckBox triggerSwitch = (CheckBox)findViewById(R.id.settings_cbox_trigger_switch);

        alarmCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _alarmSettings.setPowerSwitch(isChecked);
                _externalController.getAlarmSettingsHandler().setAlarmSettings(_device, _alarmSettings, null);
            }
        });

        triggerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _alarmSettings.setTriggerSwitch(isChecked);
                _externalController.getAlarmSettingsHandler().setAlarmSettings(_device, _alarmSettings, null);
            }
        });
    }


    private void initButtonListeners() {

        Button applyTriggerBtn = (Button)findViewById(R.id.triggers_btn_apply);

            applyTriggerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    IDataTriggerReadable trigger = createTriggerModel();
                    if(trigger != null) {
                    _externalController.getAlarmSettingsHandler().pushAlarmTrigger(_device, createTriggerModel(), new ICallback<String>() {
                        @Override
                        public void callback(CallbackException e, String data) {
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.alarm_settings_view), data, Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    });
                }}
            });

    }


    private IDataTriggerReadable createTriggerModel() {
        Spinner spinner = (Spinner)findViewById(R.id.triggers_spinner_device_feature);
        EditText limitEt = (EditText)findViewById(R.id.triggers_et_limit);
        CheckBox alarmEnabledCbox = (CheckBox)findViewById(R.id.triggers_cbox_alarm_enabled);
        CheckBox notifEnabledCbox = (CheckBox)findViewById(R.id.triggers_cbox_notifications_enabled);

        String featureId = _features.get(spinner.getSelectedItemPosition()).getId();
        double limit = 0;
        if(!limitEt.getText().toString().equals("")) {
            try {
                limit = Double.valueOf(limitEt.getText().toString());
                boolean alarmEnabled = alarmEnabledCbox.isChecked();
                boolean notifEnabled = notifEnabledCbox.isChecked();


                IDataTriggerReadable triggerModel = new DataTriggersModel(featureId, limit, alarmEnabled, notifEnabled);
                return triggerModel;
            } catch(NumberFormatException e) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.alarm_settings_view), "Limit must be a number", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        return null;
    }



    private void initNumberPicker() {
        Button plusBtn = (Button) findViewById(R.id.settings_btn_plus);
        Button minusBtn = (Button) findViewById(R.id.settings_btn_minus);
        final EditText numberTv = (EditText) findViewById(R.id.settings_np_alarm_duration);
        numberTv.setText(String.valueOf(_alarmDuration));

        numberTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              if(!s.toString().isEmpty()) {
                    _alarmSettings.setDurationSeconds(Integer.parseInt(String.valueOf(s)));
                    _externalController.getAlarmSettingsHandler().setAlarmSettings(_device, _alarmSettings, null);
                }
                else {
                    numberTv.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().isEmpty()) {

                    int duration = Integer.valueOf(s.toString());

                    if (duration > MAX_DURATION) {
                        showDialog("Maximum Duration Reached", "The max duration is 300 seconds!");
                        numberTv.setText("300");
                    }
                }
            }
        });


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _alarmSettings.setDurationSeconds(_alarmSettings.getDurationSeconds() + 1);
                _externalController.getAlarmSettingsHandler().setAlarmSettings(_device, _alarmSettings, null);
                numberTv.setText(String.valueOf(_alarmSettings.getDurationSeconds()));
            }
        });


        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_alarmSettings.getDurationSeconds() > 0) {
                    _alarmSettings.setDurationSeconds(_alarmSettings.getDurationSeconds() - 1);
                    _externalController.getAlarmSettingsHandler().setAlarmSettings(_device, _alarmSettings, null);
                    numberTv.setText(String.valueOf(_alarmSettings.getDurationSeconds()));
                }
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



    private void displaySettingsState() {

        if(initUI) {
            CheckBox alarmCheckBox = (CheckBox)findViewById(R.id.settings_cbox_enable_alarm);
            alarmCheckBox.setChecked(_alarmSettings.getPowerSwitch());

            CheckBox triggerSwitch = (CheckBox)findViewById(R.id.settings_cbox_trigger_switch);
            triggerSwitch.setChecked(_alarmSettings.getTriggerSwitch());

            final EditText numberTv = (EditText) findViewById(R.id.settings_np_alarm_duration);
            numberTv.setText(String.valueOf(_alarmSettings.getDurationSeconds()));
        }
    }


    @Override
    public void callback(CallbackException e, AlarmSettingModel data) {
        _alarmSettings = data;
        displaySettingsState();
        initUI =false;
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
                _externalController.getAlarmSettingsHandler().unRegisterAsCallback();
                onBackPressed();
                break;
            case R.id.logout_item:
                _externalController.getAccountHandler().logout();
                _externalController.getAlarmSettingsHandler().unRegisterAsCallback();
                toLoginActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
