package com.voting_app.services.interfaces;

import java.util.List;

/**
 ********************************************************************
 * Generic service interface which defines common service methods
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IGenericService<T> {

	/**
	 * Save the given object to the repository
	 * 
	 * @param obj T object to save
	 */
	void save(T obj);
	
	/**
	 * Retrieve the object for the given id from the repository
	 * 
	 * @param id to find object for
	 * @return T object for id
	 */
	T get(int id);
	
	/**
	 * Get all objects from the service
	 * 
	 * @return List<T>
	 */
	List<T> getAll();
}
