package com.tax_calculator.util.calculators;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tax_calculator.models.Citizen;
import com.tax_calculator.models.Tax;


/**
 ********************************************************************
 * @Implements INetPayCalculator
 *
 * Generic implementation of INetPayCalculator
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class NetPayCalculator implements INetPayCalculator {
	
	@Autowired
	private ITaxCalculator _taxCalc;

	@Override
	public BigDecimal calculate(List<Tax> taxRates, Citizen citizen) {
		BigDecimal salary = citizen.getSalary();
		BigDecimal totalTax = new BigDecimal(0);
		for(Tax taxRate : taxRates) {
			totalTax = totalTax.add(_taxCalc.calculate(taxRate, salary));
		}
		return salary.subtract(totalTax);
	}

}
