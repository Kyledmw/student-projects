package com.wind_speed_converter.conversion.exception;

/**
 ********************************************************************
 * An Exception which is thrown if the WindUnit conversion is not supported
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class UnitConversionException extends RuntimeException {

    private static final String UNIT_CONVERSION_ERR = "Cannot convert given unit type";

    public UnitConversionException() {
        super(UNIT_CONVERSION_ERR);
    }
}
