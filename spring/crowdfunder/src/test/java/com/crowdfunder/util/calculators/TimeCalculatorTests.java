package com.crowdfunder.util.calculators;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.util.calculators.interfaces.ITimeCalculator;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class TimeCalculatorTests {
	
	private static final int MSECONDS_IN_DAY = 86400000;

	@Autowired
	private ITimeCalculator _timeCalc;
	
	@Test
	public void timeAndDays() {
		final int numberOfDays = 7;
		Timestamp timePlusDays = _timeCalc.calcCurrentTimeAndDays(numberOfDays);
		
		int secondsToAdd = numberOfDays * MSECONDS_IN_DAY;
		Timestamp roughTimestamp = new Timestamp(System.currentTimeMillis() + secondsToAdd);

		Calendar calCalc = Calendar.getInstance();
		calCalc.setTime(timePlusDays);
		
		Calendar calRough = Calendar.getInstance();
		calRough.setTime(roughTimestamp);
		assertEquals(calCalc.get(Calendar.DATE), calRough.get(Calendar.DATE));
	}
	
}
