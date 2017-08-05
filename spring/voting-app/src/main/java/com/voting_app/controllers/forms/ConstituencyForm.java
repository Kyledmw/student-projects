package com.voting_app.controllers.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 ********************************************************************
 * Form object for creating a constituency object
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ConstituencyForm {
	
	
	@Valid
	@NotNull(message="{forms.admin.name_not_empty}")
	@Size(min = 3, max =30, message="{forms.admin.name_size}")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
