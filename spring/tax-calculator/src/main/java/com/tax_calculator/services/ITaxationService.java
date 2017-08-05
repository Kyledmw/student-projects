package com.tax_calculator.services;

import java.util.List;

import com.tax_calculator.models.Tax;

/**
 ********************************************************************
 * Interface for Taxation domain service objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ITaxationService {

	/**
	 * Save the given Tax object to the DAO.
	 * 
	 * @param obj Tax
	 */
	void save(Tax obj);
	
	/**
	 * Delete the given Tax object from the DAO.
	 * 
	 * @param obj
	 */
	void delete(Tax obj);
	
	/**
	 * Retrieve a list of all Tax objects from the DAO.
	 * 
	 * @return List of all tax objects
	 */
	List<Tax> getAll();
	
}
