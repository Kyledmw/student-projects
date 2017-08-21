package com.wind_speed_converter.units.model;

import java.util.Observable;


/**
 ********************************************************************
 * This Abstract Class links all the required inheritance for a WindUnitModel implementation.
 *
 * @extends {@link Observable}
 * @implements {@link IWindUnitsModelWriter}, {@link IWindUnitsModelReader}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public abstract class AWindUnitsModel extends Observable implements IWindUnitsModelReader, IWindUnitsModelWriter {
}
