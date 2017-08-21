package com.wind_speed_converter.units.controller;

import com.wind_speed_converter.units.model.IWindUnitsModelReader;
import com.wind_speed_converter.units.model.IWindUnitsModelWriter;

/**
 ********************************************************************
 * Interface for WindUnitModelControllers
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IWindUnitsModelController {

    /**
     * Returns an interface which will allow read access to the data model
     *
     * @return The model read access interface
     */
    public IWindUnitsModelReader getModelReader();


    /**
     * Returns an interface which will allow write access to the data model
     *
     * @return The model write access interface
     */
    public IWindUnitsModelWriter getModelWriter();
}
