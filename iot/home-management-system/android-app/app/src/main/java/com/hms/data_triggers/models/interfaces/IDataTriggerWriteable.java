package com.hms.data_triggers.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface IDataTriggerWriteable {

    void setLimit(double limit);

    void flipAlarmEnabled();

    void flipNotificationsEnabled();
}
