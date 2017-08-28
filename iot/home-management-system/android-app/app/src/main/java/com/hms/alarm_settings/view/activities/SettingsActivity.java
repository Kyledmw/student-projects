package com.hms.alarm_settings.view.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.hms_app.R;

public class SettingsActivity extends AppCompatActivity {

    private int _alarmDuration;
    private SharedPreferences.Editor _preferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _alarmDuration = 0;
        initCheckListeners();
        initNumberPicker();
        initCheckStates();

    }


    private void initCheckListeners() {
        CheckBox cameraDataCheckBox = (CheckBox)findViewById(R.id.settings_cbox_receive_camera_data);
        CheckBox recordingCheckbox = (CheckBox)findViewById(R.id.settings_cbox_recording);
        CheckBox sensorDataCheckBox = (CheckBox)findViewById(R.id.settings_cbox_receive_sensor_data);
        CheckBox alarmCheckBox = (CheckBox)findViewById(R.id.settings_cbox_enable_alarm);
        CheckBox triggerSwitch = (CheckBox)findViewById(R.id.settings_cbox_trigger_switch);

        cameraDataCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _preferencesEditor.putBoolean("cameraDataChecked", isChecked);
            }
        });

        recordingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _preferencesEditor.putBoolean("recordingChecked", isChecked);
            }
        });


        sensorDataCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _preferencesEditor.putBoolean("sensorDataChecked", isChecked);
            }
        });

        alarmCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _preferencesEditor.putBoolean("alarmChecked", isChecked);
            }
        });

        triggerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _preferencesEditor.putBoolean("triggerChecked", isChecked);
            }
        });
    }


    private void initNumberPicker() {
        Button plusBtn = (Button)findViewById(R.id.settings_btn_plus);
        Button minusBtn = (Button)findViewById(R.id.settings_btn_minus);
        final EditText numberTv = (EditText)findViewById(R.id.settings_np_alarm_duration);
        numberTv.setText(String.valueOf(_alarmDuration));

        numberTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length() == 0) {
                    numberTv.setText(String.valueOf(0));
                }
                else {
                    _alarmDuration = Integer.parseInt(String.valueOf(s));
                }
            }
        });


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _alarmDuration++;
                numberTv.setText(String.valueOf(_alarmDuration));
            }
        });


        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_alarmDuration > 0) {
                    _alarmDuration--;
                    numberTv.setText(String.valueOf(_alarmDuration));
                }
            }
        });
    }


    private void initCheckStates() {

    }
}
