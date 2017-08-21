package com.wind_speed_converter.conversion.converters;

import com.wind_speed_converter.conversion.constants.IWindConversionConstants;
import com.wind_speed_converter.conversion.models.MinMaxContainer;

import java.util.ArrayList;
import java.util.List;


/**
 ********************************************************************
 * A converter class that converts a given Knot value to its equivalent
 * beaufort and metric value.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class KnotConverter {

    private static final double MET_VAL = 1.852;

    private double _val;


    /**
     * Construct converter for given knot value
     *
     * @param val
     *          knot value to convert
     */
    protected KnotConverter(double val) {
        _val = val;
    }

    /**
     * Retrieve metric value for the knot value
     *
     * @return metric value
     */
    public double getMetricVal() {
        return (_val * MET_VAL);
    }

    /**
     * Retrieve beaufort value for the knot value
     *
     * @return beaufort value
     */
    public double getBeuaVal() {
        if(_val < 0) {
            return 0;
        }

        //Find which range the knot value falls and return the beaufort value
        for(int i = 0; i < IWindConversionConstants.beuaKnotVals.length; i++) {
            MinMaxContainer cur = IWindConversionConstants.beuaKnotVals[i];

            if(_val >= cur.getMin() && _val < cur.getMax()) {
                return (i+1);
            }
        }

        return IWindConversionConstants.MAX_BEU_NUMB;

    }


}
