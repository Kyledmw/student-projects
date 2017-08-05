package com.tax_calculator.services;

import java.util.List;

import com.tax_calculator.models.CitizenWithNetPay;

/**
 ********************************************************************
 * Interface for CitizenNetPayComposite services
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICitizenNetPayCompositeService {
	
	/**
	 * Retrieves Citizen and Tax data from their domains
	 * Generates CitizenWithNetPay objects
	 * 
	 * @return List<CitizenWithNetPay> List of citizens with their calculated net pay
	 */
	List<CitizenWithNetPay> getCitizensWithNetPay();
}
