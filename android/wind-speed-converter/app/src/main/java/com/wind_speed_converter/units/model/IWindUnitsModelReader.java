package com.wind_speed_converter.units.model;

import java.util.Observer;

/**
 ********************************************************************
 * This interface represents read access to a WindUnitsModel
 * All read access should come through this interface.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IWindUnitsModelReader {

    /**
     * Retrieve current value in km/hr
     *
     * @return metric value
     */
    public double getMetricVal();

    /**
     * Retrieve current value in knots
     *
     * @return knot value
     */
    public double getKnotsVal();

    /**
     * Retrieve current value in Beaufort
     *
     * @return beaufort value
     */
    public double getBeaufortVal();

    /**
     * Exposes addObserver method of the Observable class
     *
     * See the class comment on {@link java.util.Observable}.
     *
     * @param observer
     *              an observer to be added
     */
    public void addObserver(Observer observer);

}
