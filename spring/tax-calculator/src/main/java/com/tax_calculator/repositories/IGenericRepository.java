package com.tax_calculator.repositories;

import java.util.List;

/**
 ********************************************************************
 * Interface for a generic Repository object that provides generic methods used for the majority of repositories
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IGenericRepository<T> {
	
	/**
	 * Get object T for the given id
	 * 
	 * @param l id for the model object
	 * @return T object
	 */
	T get(long l);
	
	/**
	 * Create an object and save it
	 * 
	 * @param obj T 
	 */
	void create(T obj);
	
	/**
	 * Update the object and save it
	 *  
	 * @param obj
	 */
	void update(T obj);
	
	/**
	 * Deletes the given object from the repository
	 * 
	 * @param obj
	 */
	void delete(T obj);
	
	/**
	 * Retrieve all objects from the repositiory as a generic list
	 * 
	 * @return List<T>
	 */
	List<T> getAll();

}
