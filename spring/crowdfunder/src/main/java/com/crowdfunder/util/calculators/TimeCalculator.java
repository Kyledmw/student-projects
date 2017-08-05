package com.crowdfunder.util.calculators;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.crowdfunder.util.calculators.interfaces.ITimeCalculator;

/**
 ********************************************************************
 * Standard implementation of ITimeCalculator
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class TimeCalculator implements ITimeCalculator {

	@Override
	public Timestamp calcCurrentTimeAndDays(long days) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.add(Calendar.DAY_OF_WEEK, (int) days);
		return new Timestamp(cal.getTime().getTime());
	}

}
