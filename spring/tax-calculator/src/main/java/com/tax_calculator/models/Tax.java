package com.tax_calculator.models;

import java.math.BigDecimal;

/**
 ********************************************************************
 * Model of a taxation band
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class Tax {
	
	private long _id;
	private BigDecimal _taxFrom;
	private BigDecimal _amountEligableForTax;
	private double _taxPercent;
	private boolean _taxLimited;
	
	public long getId() {
		return _id;
	}
	
	public BigDecimal getTaxFrom() {
		return _taxFrom;
	}
	
	public BigDecimal getAmountEligableForTax() {
		return _amountEligableForTax;
	}
	
	public double getTaxPercent() {
		return _taxPercent;
	}
	
	public boolean isTaxLimited() {
		return _taxLimited;
	}
	
	public void setId(long id) {
		_id = id;
	}
	
	public void setTaxFrom(BigDecimal taxFrom) {
		_taxFrom = taxFrom;
	}
	
	public void setAmountEligableForTax(BigDecimal amountEligableForTax) {
		_amountEligableForTax = amountEligableForTax;
	}
	
	public void setTaxPercent(double taxPercent) {
		_taxPercent = taxPercent;
	}
	
	public void setTaxLimited(boolean taxLimited) {
		_taxLimited = taxLimited;
	}
}
