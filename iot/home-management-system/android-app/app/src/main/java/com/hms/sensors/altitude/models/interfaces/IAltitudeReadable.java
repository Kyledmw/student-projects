package com.hms.sensors.altitude.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface IAltitudeReadable {

    long getTimestampSeconds();

    double getValue();

    String getType();

}
