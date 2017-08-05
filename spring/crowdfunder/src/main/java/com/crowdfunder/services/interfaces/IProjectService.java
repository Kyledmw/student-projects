package com.crowdfunder.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crowdfunder.models.Project;

/**
 ********************************************************************
 * Interface for a service object for the Project domain
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IProjectService {

	/**
	 * Retrieve project page for the given pageable object
	 * 
	 * @param pageable Pageable object
	 * @return Project page
	 */
	Page<Project> getAllProjects(Pageable pageable);
	
	/**
	 * Retrieve all projects from the DAO
	 * 
	 * @return List<Project>
	 */
	List<Project> getAllProjects();
	
	/**
	 * Retrieve project for the given id from the DAO
	 * 
	 * @param id maps to projId
	 * 
	 * @return Project for the given id
	 */
	Project getProject(Long id);
	
	/**
	 * Updates statuses of projects within the DAO
	 * 
	 * This updates the completed and cancelled fields.
	 */
	void updateProjectsStatus();	
	
	/**
	 * Retrieve the amount of days left for a project before it is complete
	 * 
	 * @param proj Project to check
	 * 
	 * @return int representing the amount of days
	 */
	int getDaysLeft(Project proj);
	
	/**
	 * insert/update the Project into the DAO
	 * 
	 * @param proj Project to save
	 */
	void save(Project proj);
	
	
	/**
	 * Cancels the given project object
	 * Cancels pledges belonging to the project
	 * 
	 * @param proj Project to cancel.
	 */
	void cancelProject(Project proj);
}
