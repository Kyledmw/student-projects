package com.crowdfunder.control.util.interfaces;

import com.crowdfunder.models.Project;
/**
 ********************************************************************
 * Interface for a checking if a project is cancelable for th:if statements
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IProjectCancelable {

	/**
	 * Checks if project is cancelable
	 * 
	 * @param proj Project to check against
	 * @return boolean true if cancelable
	 */
	boolean isCancelable(Project proj);
}
