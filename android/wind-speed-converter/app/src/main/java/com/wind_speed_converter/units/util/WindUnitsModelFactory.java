package com.wind_speed_converter.units.util;

import com.wind_speed_converter.units.model.IWindUnitsModelReader;
import com.wind_speed_converter.units.model.IWindUnitsModelWriter;
import com.wind_speed_converter.units.model.WindUnitsModel;

/**
 ********************************************************************
 * A factory that is responsible for creating ModelAccess objects
 * for a created WindUnitModel object
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class WindUnitsModelFactory {

    public ModelAccess getModelAccess() {
        WindUnitsModel model = new WindUnitsModel();
        return new ModelAccess(model, model);
    }

    /**
     ********************************************************************
     * Class that exposes an AWindUnitsModel as its reader and writer interface.
     *
     * @author Kyle Williamson
     * @version 1.0.0
     ********************************************************************
     */
    public class ModelAccess {

        private IWindUnitsModelReader _reader;
        private IWindUnitsModelWriter _writer;

        public ModelAccess(IWindUnitsModelReader reader, IWindUnitsModelWriter writer) {
            _reader = reader;
            _writer = writer;
        }

        public IWindUnitsModelReader getReader() {
            return _reader;
        }

        public IWindUnitsModelWriter getWriter() {
            return _writer;
        }
    }
}
