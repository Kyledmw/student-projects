package com.wind_speed_converter.units.model;

import com.wind_speed_converter.units.constants.IModelConstants;

/**
 ********************************************************************
 * This class is the data model containing all current values of wind unit data.
 *
 * This class should be the underlying source of data for all other parts of the application.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class WindUnitsModel extends AWindUnitsModel {

    private double _metricValue;
    private double _knotsValue;
    private double _beaufortValue;

    @Override
    public double getMetricVal() {
        return _metricValue;
    }

    @Override
    public double getKnotsVal() {
        return _knotsValue;
    }

    @Override
    public double getBeaufortVal() {
        return _beaufortValue;
    }

    @Override
    public void setMetricVal(double val) {
        _metricValue = val;
        performNotification(IModelConstants.MODEL_CHANGE_PROPERTIES);
    }

    @Override
    public void setKnotsVal(double val) {
        _knotsValue = val;
        performNotification(IModelConstants.MODEL_CHANGE_PROPERTIES);
    }

    @Override
    public void setBeaufortVal(double val) {
        _beaufortValue = val;
        performNotification(IModelConstants.MODEL_CHANGE_PROPERTIES);
    }

    /**
     * Perform notifications to all observers with a given argument
     * @param update
     *          ModelConstant used to signify the type of update
     */
    private void performNotification(String update) {
        setChanged();
        notifyObservers(update);
        clearChanged();
    }
}
