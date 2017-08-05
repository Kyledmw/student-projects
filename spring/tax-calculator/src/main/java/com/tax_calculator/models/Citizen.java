package com.tax_calculator.models;

import java.math.BigDecimal;

/**
 ********************************************************************
 * Model of a generic Citizen
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class Citizen {

	private long _id;
	private String _name;
	private BigDecimal _salary;
	
	public long getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public BigDecimal getSalary() {
		return _salary;
	}
	
	public void setId(long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}
	
	public void setSalary(BigDecimal salary) {
		_salary = salary;
	}

}
