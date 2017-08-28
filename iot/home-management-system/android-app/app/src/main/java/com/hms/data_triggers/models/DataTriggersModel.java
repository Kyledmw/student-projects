package com.hms.data_triggers.models;

import com.hms.data_triggers.models.interfaces.IDataTriggerReadable;
import com.hms.data_triggers.models.interfaces.IDataTriggerWriteable;

/**
 * Created by Mark on 13/02/2016.
 */
public class DataTriggersModel implements IDataTriggerReadable, IDataTriggerWriteable {

    private String _id;
    private String _featureTypeId;
    private double _limit;
    private boolean _alarmEnabled;
    private boolean _notificationsEnabled;


    public DataTriggersModel(String featureTypeId, double limit, boolean alarmEnabled, boolean notificationsEnabled){
       this(null, featureTypeId, limit, alarmEnabled, notificationsEnabled);
    }

    public DataTriggersModel(String id, String featureTypeId, double limit, boolean alarmEnabled, boolean notificationsEnabled){
        _id = id;
        _featureTypeId = featureTypeId;
        _limit = limit;
        _alarmEnabled =  alarmEnabled;
        _notificationsEnabled = notificationsEnabled;
    }

    @Override
    public String toString(){
        return "ID: " + _id + " Feature Type: " + _featureTypeId + " Limit: " + _limit + " Alarm Enabled: " + _alarmEnabled + " Notifications Enabled: " + _notificationsEnabled;

    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public String getFeatureTypeId() {
        return _featureTypeId;
    }

    @Override
    public double getLimit() {
        return _limit;
    }

    @Override
    public boolean getAlarmEnabled() {
        return _alarmEnabled;
    }

    @Override
    public boolean getNotificationsEnabled() {
        return _notificationsEnabled;
    }

    @Override
    public void setLimit(double limit) {
        _limit = limit;
    }

    @Override
    public void flipAlarmEnabled() {
        _alarmEnabled = !_alarmEnabled;
    }

    @Override
    public void flipNotificationsEnabled() {
        _notificationsEnabled = !_notificationsEnabled;
    }
}
