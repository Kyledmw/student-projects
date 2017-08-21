package com.wind_speed_converter.conversion.converters;

import com.wind_speed_converter.conversion.constants.IWindConversionConstants;
import com.wind_speed_converter.conversion.models.MinMaxContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 ********************************************************************
 * A converter class that converts a given Beaufort value to its equivalent
 * metric and knot value.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class BeaufortConverter {

    private int _val;

    /**
     * Construct converter for given beaufort value
     *
     * @param val
     *          beaufort value to convert
     */
    protected BeaufortConverter(double val) {
        _val = (int) val;
    }

    /**
     * Retrieve metric value for the beaufort value
     *
     * @return metric value
     */
    public double getMetricVal() {
        return getVal(IWindConversionConstants.beuaMetricVals);
    }


    /**
     * Retrieve knot value for the beaufort value
     *
     * @return knot value
     */
    public double getKnotVal() {
        return getVal(IWindConversionConstants.beuaKnotVals);
    }

    /**
     * Method that returns the average value of the range in which is equal to the given beaufort value
     *
     * @param minMaxArr
     *          Array of MinMax value that represent the range of a beaufort value
     *
     * @return average of the range of values
     */
    private double getVal(MinMaxContainer[] minMaxArr) {
        if(_val <= 0 ) {
            return 0;
        } else if(_val > minMaxArr.length) {
            MinMaxContainer minMax = minMaxArr[minMaxArr.length -1];
            return (minMax.getMax() + minMax.getMin()) /2;
        }

        MinMaxContainer minMax = minMaxArr[_val - 1];
        return (minMax.getMax() + minMax.getMin()) /2;

    }
}
