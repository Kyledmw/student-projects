package com.hms.sensors.temperature.models;

import com.hms.sensors.temperature.models.interfaces.ITemperatureReadable;

/**
 * Created by Mark on 13/02/2016.
 */


public class TemperatureModel implements ITemperatureReadable {

    private long _timestamp;
    private double _value;
    private String _type;

    public TemperatureModel(long timestamp, double value, String type){
        _timestamp = timestamp;
        _value = value;
        _type = type;
    }

    @Override
    public String toString(){
        return "Timestamp (Seconds): " + _timestamp + " Value: " + _value + " Type: " + _type;
    }


    @Override
    public long getTimestampSeconds() {
        return _timestamp;
    }

    @Override
    public double getValue() {
        return _value;
    }

    @Override
    public String getType() {
        return _type;
    }
}
