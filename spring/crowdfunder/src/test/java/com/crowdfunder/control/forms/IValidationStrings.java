package com.crowdfunder.control.forms;

/**
 ********************************************************************
 * Interface representing expected Strings from ValidationMessages.properties
 * 
 * This is used for testing validation of forms and custom validators.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IValidationStrings {
	
	/*
	 * User Form Validation Strings
	 */
	
	String USERFORM_USERNAME_NULL = "Username cannot be empty.";
	String USERFORM_USERNAME_SIZE = "Username must be between 3 and 30 characters.";
	
	String USERFORM_PASSWORD_NULL = "Password cannot be empty.";
	String USERFORM_PASSWORD_SIZE = "Password must be between 6 and 30 characters.";
	
	String USERFORM_PASSWORDCONFIRM_NULL = "Password confirmation cannot be empty.";
	String USERFORM_PASSWORDCONFIRM_SIZE = "Password confirmation must be between 6 and 30 characters.";
	
	String USERFORM_EMAIL_NULL = "Email cannot be empty.";
	String USERFORM_EMAIL_VALID = "Valid email required.";
	
	String USERFORM_CREDITLIMIT_MINMAX = "Credit limit must be between $50 and $5000.";
	String USERFORM_PASSWORD_CONFIRM = "Password and password confirmation must be equal.";
	
	/*
	 * Project Form Validation Strings
	 */

	String PROJECTFORM_PROJNAME_NULL = "Project name cannot be empty.";
	String PROJECTFORM_PROJNAME_SIZE = "Project name must be between 3 and 30 characters.";
	
	String PROJECTFORM_DESC_NULL = "Project description cannot be empty.";
	String PROJECTFORM_DESC_SIZE = "Project description must be between 10 and 100 characters.";
	
	String PROJECTFORM_YTUBELINK_NULL = "Project YouTube link cannot be empty.";
	
	String PROJECT_FORM_GOALAMOUNT_MINMAX = "Goal must be between $1000 and $100000.";
	
	String PROJECT_FORM_DURATION_MINMAX = "Duration must be between 7 and 30 days.";
	
	String CUSTOM_YTUBE_VALID = "YouTube link must be valid.";
}
