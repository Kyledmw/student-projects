package com.hms.data_triggers.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface IDataTriggerReadable {

    String getId();

    String getFeatureTypeId();

    double getLimit();

    boolean getAlarmEnabled();

    boolean getNotificationsEnabled();
}
