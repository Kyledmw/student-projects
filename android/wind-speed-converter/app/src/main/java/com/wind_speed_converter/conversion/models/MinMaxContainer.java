package com.wind_speed_converter.conversion.models;

/**
 ********************************************************************
 * A Container class that stores a min and max double value
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class MinMaxContainer {

    private double _min;
    private double _max;

    public MinMaxContainer(double min, double max) {
        _min = min;
        _max = max;
    }

    public double getMax() {
        return _max;
    }

    public double getMin() {
        return _min;
    }
}
