package com.tax_calculator.models.util;

import java.util.Comparator;

import com.tax_calculator.models.CitizenWithNetPay;

/**
 ********************************************************************
 * @implements Comparator<CitizenWithNetPay>
 * 
 * Comparator for sorting collections of CitizenWithNetPay objects off their id field
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CitizenNetPayComparatorOnId implements Comparator<CitizenWithNetPay> {

	@Override
	public int compare(CitizenWithNetPay o1, CitizenWithNetPay o2) {
		return (int) (o1.getCitizen().getId() - o2.getCitizen().getId());
	}

}
