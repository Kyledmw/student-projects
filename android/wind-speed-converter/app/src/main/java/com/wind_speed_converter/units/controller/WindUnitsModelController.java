package com.wind_speed_converter.units.controller;

import com.wind_speed_converter.units.model.IWindUnitsModelReader;
import com.wind_speed_converter.units.model.IWindUnitsModelWriter;
import com.wind_speed_converter.units.util.WindUnitsModelFactory;

/**
 ********************************************************************
 * This is the controller class that links the WindUnit data model with the rest of the application.
 *
 * This a singleton implementation of {@link IWindUnitsModelController}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class WindUnitsModelController implements IWindUnitsModelController {

    private static WindUnitsModelController instance = new WindUnitsModelController();

    private IWindUnitsModelReader _reader;
    private IWindUnitsModelWriter _writer;

    private WindUnitsModelController() {
        //Create model access object
        WindUnitsModelFactory.ModelAccess modelAccess = new WindUnitsModelFactory().getModelAccess();

        _reader = modelAccess.getReader();
        _writer = modelAccess.getWriter();
    }

    /**
     * Retrieve singleton instance of this class
     *
     * @return WindUnitsModelController
     *                  The singleton instance of the controller
     */
    public static WindUnitsModelController getInstance() {
        return instance;
    }

    @Override
    public IWindUnitsModelReader getModelReader() {
        return _reader;
    }

    @Override
    public IWindUnitsModelWriter getModelWriter() {
        return _writer;
    }
}
