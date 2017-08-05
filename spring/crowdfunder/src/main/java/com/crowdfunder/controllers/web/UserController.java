package com.crowdfunder.controllers.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdfunder.control.forms.EditProjectForm;
import com.crowdfunder.control.forms.ProjectForm;
import com.crowdfunder.control.util.ProjectCancelable;
import com.crowdfunder.controllers.constants.IControllerConstants;
import com.crowdfunder.models.User;
import com.crowdfunder.services.interfaces.IUserSecurityService;
import com.crowdfunder.util.calculators.interfaces.IPledgeCalculator;

/**
 ********************************************************************
 * This controller handles non-auth related user requests
 *
 * This handles WebMVC requests related to the User domain
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class UserController extends ALayoutController {
	
	private static final String ROUTE_ID = "user_page";
	
	private static final String DASHB_VIEW = "user_dashboard";
	
	private static final String PROJECTS_PARAM = "projects";
	private static final String PLEDGES_PARAM = "pledges";
	private static final String AMT_AVAIL_PLEDGE_PARAM = "amountAvailPledge";
	
	private static final String PROJECT_CANCELABLE_PARAM = "projCancelable";
	
	
	@Autowired
	private IUserSecurityService _userSecServ;
	
	@Autowired
	private IPledgeCalculator _pledgeCalc;

	/**
	 * Request handler for retrieving the dashboard view.
	 * 
	 * @param model Model managed by Spring Web MVC
	 * @param locale Locale object to allow for localisation
	 * 
	 * @return dashboard view, refer to resources/templates/dashboard.html
	 */
	@RequestMapping(value= "/dashboard", method = RequestMethod.GET)
	public String view(Model model, Locale locale) {	
		super.setCurrentRoute(ROUTE_ID);
		super.prepareModel(model);
		if(!model.containsAttribute(IControllerConstants.PROJECT_FORM)){
			model.addAttribute(IControllerConstants.PROJECT_FORM, new ProjectForm());
		}
		
		if(!model.containsAttribute(IControllerConstants.EDIT_PROJ_FORM)) {
			model.addAttribute(IControllerConstants.EDIT_PROJ_FORM, new EditProjectForm());
		}
		
		User user = _userSecServ.getLoggedInUser();
		model.addAttribute(PROJECT_CANCELABLE_PARAM , new ProjectCancelable());
		model.addAttribute(PROJECTS_PARAM, user.getProjects());
		model.addAttribute(PLEDGES_PARAM, user.getPledges());
		model.addAttribute(AMT_AVAIL_PLEDGE_PARAM, _pledgeCalc.calculateAvailableAmountToPledge(user));
		return DASHB_VIEW;
	}

}
