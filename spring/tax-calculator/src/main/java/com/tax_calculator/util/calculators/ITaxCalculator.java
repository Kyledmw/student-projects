package com.tax_calculator.util.calculators;

import java.math.BigDecimal;

import com.tax_calculator.models.Tax;

/**
 ********************************************************************
 *
 * Interface to calculate the amount of tax owed for a given rate and salary.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ITaxCalculator {

	/**
	 * Calculate the tax required for the given tax rate
	 * 
	 * @param tax Taxation rate owed
	 * @param salary Salary to calculate tax off
	 * @return Calculated tax owed
	 */
	BigDecimal calculate(Tax tax, BigDecimal salary);
}
