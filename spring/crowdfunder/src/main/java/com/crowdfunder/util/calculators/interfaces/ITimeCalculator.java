package com.crowdfunder.util.calculators.interfaces;

import java.sql.Timestamp;

/**
 ********************************************************************
 * Interface for calculators focused on calculating TimeStamp related data.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ITimeCalculator {

	/**
	 * Calculate a Timestamp object from the current time with given days.
	 * 
	 * @param days amount of days to add to the Timestamp object
	 * 
	 * @return Timestamp object representing current time with given days
	 */
	Timestamp calcCurrentTimeAndDays(long days);
}
