package com.wind_speed_converter.conversion.converters;

import com.wind_speed_converter.conversion.constants.IWindConversionConstants;
import com.wind_speed_converter.conversion.models.MinMaxContainer;

/**
 ********************************************************************
 * A converter class that converts a given Metric value to its equivalent
 * beaufort and knot value.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class MetricConverter {

    private static final double KNOT_VAL = 0.53996;

    private double _val;

    /**
     * Construct converter for given metric value
     *
     * @param val
     *          metric value to convert
     */
    protected MetricConverter(double val) {
        _val = val;
    }

    /**
     * Retrieve knot value for the metric value
     *
     * @return knot value
     */
    public double getKnotVal() {
        return (_val * KNOT_VAL);
    }


    /**
     * Retrieve beaufort value for the metric value
     *
     * @return beaufort value
     */
    public double getBeuaVal() {
        if(_val <= 0) {
            return 0;
        }

        //Find which range the knot value falls and return the beaufort value
        for(int i = 0; i < IWindConversionConstants.beuaMetricVals.length; i++) {
            MinMaxContainer cur = IWindConversionConstants.beuaMetricVals[i];

            if(_val >= cur.getMin() && _val < cur.getMax()) {
                return (i+1);
            }
        }

        return IWindConversionConstants.MAX_BEU_NUMB;

    }
}
