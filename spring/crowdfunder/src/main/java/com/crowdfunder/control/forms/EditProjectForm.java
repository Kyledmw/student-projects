package com.crowdfunder.control.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 ********************************************************************
 * Class representing a form for editing a project.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class EditProjectForm {
	
	@Valid
	@NotNull(message = "{projectForm.desc.null}")
	@Size(min=10, max=100, message = "{projectForm.desc.size}")
	private String desc;
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
