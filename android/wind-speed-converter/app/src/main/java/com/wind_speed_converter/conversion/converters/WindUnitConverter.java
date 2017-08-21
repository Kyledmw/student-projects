package com.wind_speed_converter.conversion.converters;

import com.wind_speed_converter.conversion.constants.IWindConversionConstants;
import com.wind_speed_converter.conversion.exception.UnitConversionException;

import java.util.HashMap;
import java.util.Map;

/**
 ********************************************************************
 * A converter class that converts between different WindUnits
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class WindUnitConverter {

    /**
     * Convert given value to other supported WindUnitTypes
     *
     * @param type
     *          {@link IWindConversionConstants.WindUnitType} type of variable val
     * @param val
     *          value of the current WindUnitType given
     *
     * @return Map containing converted values using their relevant WindUnitTypes as keys
     */
    public Map<IWindConversionConstants.WindUnitType, Double> convert(IWindConversionConstants.WindUnitType type, double val) {
        switch (type) {
            case METRIC:
                        return metricConversion(val);
            case KNOT:
                        return knotConversion(val);
            case BEAUFORT:
                        return beaufortConversion(val);
        }

        //if type not supported throw error
        throw new UnitConversionException();
    }

    /**
     * Method that performs conversions if value is metric
     *
     * @param val value to be converted
     * @return Map containing converted values using their relevant WindUnitTypes as keys
    */
    private Map<IWindConversionConstants.WindUnitType, Double> metricConversion(double val) {
        Map<IWindConversionConstants.WindUnitType, Double> map = new HashMap<IWindConversionConstants.WindUnitType, Double>();
        map.put(IWindConversionConstants.WindUnitType.METRIC, val);

        MetricConverter conv = new MetricConverter(val);
        map.put(IWindConversionConstants.WindUnitType.KNOT, conv.getKnotVal());
        map.put(IWindConversionConstants.WindUnitType.BEAUFORT, conv.getBeuaVal());

        return map;
    }


    /**
     * Method that performs conversions if value is knot
     *
     * @param val value to be converted
     * @return Map containing converted values using their relevant WindUnitTypes as keys
     */
    private Map<IWindConversionConstants.WindUnitType, Double> knotConversion(double val) {
        Map<IWindConversionConstants.WindUnitType, Double> map = new HashMap<IWindConversionConstants.WindUnitType, Double>();
        map.put(IWindConversionConstants.WindUnitType.KNOT, val);

        KnotConverter conv = new KnotConverter(val);
        map.put(IWindConversionConstants.WindUnitType.METRIC, conv.getMetricVal());
        map.put(IWindConversionConstants.WindUnitType.BEAUFORT, conv.getBeuaVal());

        return map;

    }


    /**
     * Method that performs conversions if value is beaufort
     *
     * @param val value to be converted
     * @return Map containing converted values using their relevant WindUnitTypes as keys
     */
    private Map<IWindConversionConstants.WindUnitType, Double> beaufortConversion(double val) {
        Map<IWindConversionConstants.WindUnitType, Double> map = new HashMap<IWindConversionConstants.WindUnitType, Double>();

        //Since max Beaufort value is 12, if value entered is greater than 12 set it to 12
        val = (val > 12) ? 12 : val;
        map.put(IWindConversionConstants.WindUnitType.BEAUFORT, val);

        BeaufortConverter conv = new BeaufortConverter(val);
        map.put(IWindConversionConstants.WindUnitType.KNOT, conv.getKnotVal());
        map.put(IWindConversionConstants.WindUnitType.METRIC, conv.getMetricVal());

        return map;

    }

}
