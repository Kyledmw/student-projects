package com.hms.alarm_settings.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface IAlarmSettingsWriteable {

    void flipPowerSwitch();

    void flipTriggerSwitch();

    void setDurationSeconds(int duration);

    void setPowerSwitch(boolean switchState);

    void setTriggerSwitch(boolean triggerState);
}
