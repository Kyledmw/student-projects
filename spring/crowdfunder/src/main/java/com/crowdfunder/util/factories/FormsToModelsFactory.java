package com.crowdfunder.util.factories;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crowdfunder.control.forms.PledgeForm;
import com.crowdfunder.control.forms.ProjectForm;
import com.crowdfunder.control.forms.UserForm;
import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.Project;
import com.crowdfunder.models.User;
import com.crowdfunder.util.calculators.interfaces.ITimeCalculator;
import com.crowdfunder.util.factories.interfaces.IFormsToModelsFactory;
import com.crowdfunder.util.parsers.interfaces.IYTLinkParser;


/**
 ********************************************************************
 * Standard implementation of IFormsToModelsFactory
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class FormsToModelsFactory implements IFormsToModelsFactory {
	
	@Autowired
	private ITimeCalculator _timeCalc;
	
	@Autowired
	private IYTLinkParser _ytLinkParser;

	@Override
	public User userFormToUser(UserForm userForm) {
		User user = new User();
		
		user.setEmail(userForm.getEmail());
		user.setUserName(userForm.getUserName());
		user.setPassword(userForm.getPassword());
		user.setPasswordConfirm(userForm.getPasswordConfirm());
		user.setCreditLimit(userForm.getCreditLimit());
		
		return user;
	}

	@Override
	public Project projectFormToProject(ProjectForm projectForm) {
		Project proj = new Project();
		
		proj.setCancelled(false);
		proj.setCompleted(false);
		
		proj.setImageName(projectForm.getImage().getName());
		proj.setFinishTime(_timeCalc.calcCurrentTimeAndDays(projectForm.getDuration()));
		proj.setDescription(projectForm.getDesc());
		proj.setGoalAmount(projectForm.getGoalAmount());
		proj.setName(projectForm.getProjName());
		proj.setYTVidCode(_ytLinkParser.parseLinkForVidCode(projectForm.getYTubeLink()));
		proj.setPledges(new ArrayList<Pledge>());
		return proj;
	}

	@Override
	public Pledge pledgeFormToPledge(PledgeForm pledgeForm) {
		Pledge pledge = new Pledge();
		
		pledge.setActive(true);
		pledge.setAmount(pledgeForm.getAmount());
		return pledge;
	}

}
