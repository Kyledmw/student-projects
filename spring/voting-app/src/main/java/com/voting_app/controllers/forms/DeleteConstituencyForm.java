package com.voting_app.controllers.forms;

import javax.validation.constraints.Min;

/**
 ********************************************************************
 * Form object for deleting a constituency object
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class DeleteConstituencyForm {


	@Min(value=1)
	private int _id;
	
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		_id = id;
	}
	
}
