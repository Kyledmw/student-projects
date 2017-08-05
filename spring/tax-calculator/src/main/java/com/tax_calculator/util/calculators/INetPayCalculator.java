package com.tax_calculator.util.calculators;

import java.math.BigDecimal;
import java.util.List;

import com.tax_calculator.models.Citizen;
import com.tax_calculator.models.Tax;

/**
 ********************************************************************
 *
 * Interface for calculating Citizens net pay off a collection of tax rates
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface INetPayCalculator {

	/**
	 * Calculates a citizens net pay
	 * 
	 * @param taxRates List of tax rates to be used in the calculation
	 * @param citizen Citizen to calculate net pay of
	 * @return BigDecimal representing the Citizens net pay
	 */
	BigDecimal calculate(List<Tax> taxRates, Citizen citizen);
}
