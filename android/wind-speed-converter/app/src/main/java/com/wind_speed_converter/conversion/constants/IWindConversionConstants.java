package com.wind_speed_converter.conversion.constants;


import com.wind_speed_converter.conversion.models.MinMaxContainer;

/**
 ********************************************************************
 * List of constants used by conversion classes
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IWindConversionConstants {

    public static final double MAX_BEU_NUMB = 12;

    public static final MinMaxContainer[] beuaKnotVals = {
            new MinMaxContainer(0, 1),
            new MinMaxContainer(1, 4),
            new MinMaxContainer(4, 7),
            new MinMaxContainer(7, 11),
            new MinMaxContainer(11, 16),
            new MinMaxContainer(16, 22),
            new MinMaxContainer(22, 28),
            new MinMaxContainer(28, 34),
            new MinMaxContainer(34, 41),
            new MinMaxContainer(41, 48),
            new MinMaxContainer(48, 56),
            new MinMaxContainer(56, 63)
    };

    public static final MinMaxContainer[] beuaMetricVals = {
            new MinMaxContainer(0, 1.9),
            new MinMaxContainer(1.9, 6.5),
            new MinMaxContainer(6.5, 12.1),
            new MinMaxContainer(12.1, 19.5),
            new MinMaxContainer(19.5, 28.8),
            new MinMaxContainer(28.8, 39.9),
            new MinMaxContainer(39.9, 51),
            new MinMaxContainer(51, 62.1),
            new MinMaxContainer(62.1, 75.1),
            new MinMaxContainer(75.1, 88),
            new MinMaxContainer(88., 102.9),
            new MinMaxContainer(102.9, 117.6)
    };

    public enum WindUnitType {
        METRIC,
        KNOT,
        BEAUFORT
    }


}
