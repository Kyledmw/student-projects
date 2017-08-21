package com.wind_speed_converter.units.model;


/**
 ********************************************************************
 * This interface represents write access to a WindUnitsModel
 * All write access should come through this interface.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IWindUnitsModelWriter {

    /**
     * Update metric value in the model
     *
     * @param val
     *          metric value
     */
    public void setMetricVal(double val);

    /**
     * Update knot value in the model
     *
     * @param val
     *          knot value
     */
    public void setKnotsVal(double val);

    /**
     * Update beaufort value in the model
     *
     * @param val
     *          beaufort value
     */
    public void setBeaufortVal(double val);
}
