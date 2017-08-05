package com.crowdfunder.util.factories.interfaces;

import com.crowdfunder.control.forms.PledgeForm;
import com.crowdfunder.control.forms.ProjectForm;
import com.crowdfunder.control.forms.UserForm;
import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.Project;
import com.crowdfunder.models.User;

/**
 ********************************************************************
 * Interface which consists of factory methods for building domain models off their relevant forms
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IFormsToModelsFactory {

	/**
	 * Create a user model object from a UserForm
	 * 
	 * @param userForm form representing a new user to create
	 * 
	 * @return newly created user model
	 */
	public User userFormToUser(UserForm userForm);
	
	/**
	 * Create a project model object from a ProjectForm
	 * 
	 * @param projectForm form representing a new project to create
	 * 
	 * @return newly created project model
	 */
	public Project projectFormToProject(ProjectForm projectForm);
	
	
	/**
	 * Create a plage model object from a PledgeForm
	 * 
	 * @param pledgeForm form representing a new pledge to create
	 * 
	 * @return newly created pledge model
	 */
	public Pledge pledgeFormToPledge(PledgeForm pledgeForm);
}
