package com.crowdfunder.control.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crowdfunder.control.validation.annotations.ProjectPledgeable;
import com.crowdfunder.models.Project;
import com.crowdfunder.services.interfaces.IProjectService;
import com.crowdfunder.util.helpers.interfaces.IPledgeHelper;

/**
 ********************************************************************
 * Custom hibernate validator that checks if a project is pledgeable
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class ProjectPledgeableValidator implements ConstraintValidator<ProjectPledgeable, Long> {
	
	@Autowired
	private IProjectService _projServ;
	
	@Autowired 
	private IPledgeHelper _pledgeHelper;

	@Override
	public void initialize(ProjectPledgeable constraintAnnotation) {
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		Project proj = _projServ.getProject(value);
		return _pledgeHelper.projectPledgeable(proj);
	}

}
