package com.hms.alarm_settings.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface IAlarmSettingsReadable {

    String getId();

    boolean getPowerSwitch();

    boolean getTriggerSwitch();

    int getDurationSeconds();

    String toString();

}
