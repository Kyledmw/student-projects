package com.tax_calculator.models;

import java.math.BigDecimal;

/**
 ********************************************************************
 * Model of a citizen with their calculated net pay.
 * 
 * This model is not a domain model object.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CitizenWithNetPay {

	private Citizen _citizen;
	private BigDecimal _netPay;
	
	public Citizen getCitizen() {
		return _citizen;
	}
	
	public BigDecimal getNetPay() {
		return _netPay;
	}
	
	public void setCitizen(Citizen citizen) {
		_citizen = citizen;
	}
	
	public void setNetPay(BigDecimal netPay) {
		_netPay = netPay;
	}
}
