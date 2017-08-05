package com.tax_calculator.util.calculators;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.stereotype.Component;

import com.tax_calculator.models.Tax;


/**
 ********************************************************************
 * @Implements ITaxCalculator
 *
 * Generic implementation of ITaxCalculator
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class TaxCalculator implements ITaxCalculator{

	@Override
	public BigDecimal calculate(Tax tax, BigDecimal salary) {
		BigDecimal taxableAmount = salary.subtract(tax.getTaxFrom());
		if(taxableAmount.compareTo(new BigDecimal(0)) != 1) {
			return new BigDecimal(0);
		}
		
		BigDecimal nonTaxableAmount = taxableAmount.subtract(tax.getAmountEligableForTax());
		if(tax.isTaxLimited() && (nonTaxableAmount.compareTo(new BigDecimal(0)) != -1)) {
			taxableAmount = tax.getAmountEligableForTax();
		}
		
		return taxableAmount.multiply(new BigDecimal(tax.getTaxPercent(), MathContext.DECIMAL64));
	}

}