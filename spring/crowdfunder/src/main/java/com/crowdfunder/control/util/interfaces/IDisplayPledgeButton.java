package com.crowdfunder.control.util.interfaces;

import com.crowdfunder.models.Project;

/**
 ********************************************************************
 * Interface for checking if the display pledge button should be visisble on a view
 * 
 * Used for th:if statements
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IDisplayPledgeButton {

	/**
	 * Check if the button should be displayed
	 * 
	 * @param proj Project to be used when checking
	 * @return boolean to display or nto
	 */
	boolean display(Project proj);
}
