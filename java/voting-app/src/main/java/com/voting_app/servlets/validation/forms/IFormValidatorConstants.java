package com.voting_app.servlets.validation.forms;

/**
 ********************************************************************
 * Interface which contains constants used throughout the form validators
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IFormValidatorConstants {
	
	String UNIQUE_NAME = "Name must be unique";
	String INVALID_VOTE = "Invalid Vote";
	String SEQUENTIAL_VOTES = "Votes must be sequential e.g. 1, 2, 3";
}
