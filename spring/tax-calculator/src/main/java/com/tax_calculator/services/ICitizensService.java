package com.tax_calculator.services;

import java.util.List;

import com.tax_calculator.models.Citizen;
/**
 ********************************************************************
 * Interface for Citizen domain service objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICitizensService {
	
	/**
	 *  Saves the Citizen object to the DAO
	 *  
	 * @param obj Citizen object
	 */
	void save(Citizen obj);
	
	/**
	 * Retrieve a list of all Citizens from the DAO.
	 * 
	 * @return List of all Citizens 
	 */
	List<Citizen> getAll();

}
