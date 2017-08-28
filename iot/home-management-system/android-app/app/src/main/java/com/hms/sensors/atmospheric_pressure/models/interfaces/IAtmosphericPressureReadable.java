package com.hms.sensors.atmospheric_pressure.models.interfaces;

/**
 * Created by Kyle on 13/02/2016.
 */
public interface IAtmosphericPressureReadable {

    long getTimestampSeconds();

    double getValue();

    String getType();

    String toString();
}
