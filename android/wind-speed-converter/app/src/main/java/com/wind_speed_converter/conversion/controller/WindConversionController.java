package com.wind_speed_converter.conversion.controller;


import com.wind_speed_converter.conversion.constants.IWindConversionConstants;
import com.wind_speed_converter.conversion.converters.WindUnitConverter;
import com.wind_speed_converter.units.model.IWindUnitsModelWriter;

import java.util.Map;

/**
 ********************************************************************
 * A controller that handles WindUnit conversions, it writes these conversion to
 * the data model through the {@link IWindUnitsModelWriter} interface.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class WindConversionController {

    private WindUnitConverter _converter;
    private IWindUnitsModelWriter _writer;
    private IWindConversionConstants.WindUnitType _type;

    /**
     *
     * @param writer model writer interface
     */
    public WindConversionController(IWindUnitsModelWriter writer) {
        _converter = new WindUnitConverter();
        _writer = writer;
    }

    /**
     * Set current wind unit type to convert to
     *
     * @param type type of WindUnit {@link IWindConversionConstants.WindUnitType}
     */
    public void setType(IWindConversionConstants.WindUnitType type) {
        _type = type;
    }

    /**
     * Retrieve current WindUnitType
     *
     * @return current wind unit type
     */
    public IWindConversionConstants.WindUnitType getType() {
        return _type;
    }

    /**
     * Convert given value of current type
     * Writes the converted values to the model
     *
     * @param val value to convert
     */
    public void performConversion(double val) {
        Map<IWindConversionConstants.WindUnitType, Double> convertedVals = _converter.convert(_type, val);

        _writer.setMetricVal(convertedVals.get(IWindConversionConstants.WindUnitType.METRIC));
        _writer.setKnotsVal(convertedVals.get(IWindConversionConstants.WindUnitType.KNOT));
        _writer.setBeaufortVal(convertedVals.get(IWindConversionConstants.WindUnitType.BEAUFORT));
    }

    /**
     * Add multiple values of different WindUnitTypes.
     * The result will be given in the current WindUnitType
     * It will then perform a conversion on the result
     *
     * @param valsToAdd map containing values with their relevant WindUnitType keys
     *
     * @return result value of all units added
     */
    public double performAddition(Map<IWindConversionConstants.WindUnitType, Double> valsToAdd) {
        double total = 0;
        for(Map.Entry<IWindConversionConstants.WindUnitType, Double> cur: valsToAdd.entrySet()) {
            total += _converter.convert(cur.getKey(), cur.getValue()).get(_type);
        }
        performConversion(total);

        if(_type == IWindConversionConstants.WindUnitType.BEAUFORT && total > 12) {
            total = 12;
        }

        return total;
    }
}
