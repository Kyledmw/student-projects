package com.hms.alarm_settings.models;

import com.hms.alarm_settings.models.interfaces.IAlarmSettingsReadable;
import com.hms.alarm_settings.models.interfaces.IAlarmSettingsWriteable;

/**
 * Created by Mark on 13/02/2016.
 */
public class AlarmSettingModel implements IAlarmSettingsReadable, IAlarmSettingsWriteable {

    private String _id;
    private boolean _powerSwitch;
    private boolean _triggerSwitch;
    private int _duration;


    public AlarmSettingModel(String id, boolean powerSwitch, boolean triggerSwitch, int duration){
        _id = id;
        _powerSwitch = powerSwitch;
        _triggerSwitch = triggerSwitch;
        _duration = duration;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public boolean getPowerSwitch() {
        return _powerSwitch;
    }

    @Override
    public boolean getTriggerSwitch() {
        return _triggerSwitch;
    }

    @Override
    public int getDurationSeconds() {
        return _duration;
    }

    @Override
    public String toString() {
        return "ID: " + _id + " Power Switch: " + _powerSwitch + " Trigger Switch: " +  _triggerSwitch + " Duration: " + _duration;
    }

    @Override
    public void flipPowerSwitch() {
        _powerSwitch = !_powerSwitch;
    }

    @Override
    public void flipTriggerSwitch() {
        _triggerSwitch = !_triggerSwitch;
    }

    @Override
    public void setDurationSeconds(int duration) {
        _duration = duration;

    }

    @Override
    public void setPowerSwitch(boolean switchState) {
        _powerSwitch = switchState;
    }

    @Override
    public void setTriggerSwitch(boolean triggerState) {
        _triggerSwitch = triggerState;
    }
}
