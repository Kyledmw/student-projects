package com.hms.sensors.temperature.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface ITemperatureReadable {

    long getTimestampSeconds();

    double getValue();

    String getType();
}
