package com.crowdfunder.controllers.web;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdfunder.control.forms.UserForm;
import com.crowdfunder.controllers.constants.IControllerConstants;
import com.crowdfunder.models.User;
import com.crowdfunder.services.interfaces.ISecurityService;
import com.crowdfunder.services.interfaces.IUserService;
import com.crowdfunder.util.factories.interfaces.IFormsToModelsFactory;

/**
 ********************************************************************
 * WebMVC Controller that handles auth related HTTP requests.
 * 
 * Handles /registration get and post
 * Handles /login get, /login post handles by Spring Security
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class AuthController extends ALayoutController {

	@Autowired
	private IUserService _userServ;
	
	@Autowired
	private ISecurityService _secServ;
	
	@Autowired
	private IFormsToModelsFactory _formToModelFactory;
	
	private static final String REG_VIEW = "registration";
	private static final String LOGIN_VIEW = "login";
	
	private static final String USER_FORM = "userForm";
	
	/**
	 * Request hander for retrieving the registration view.
	 * 
	 * @param model Model managed by Spring Web MVC
	 * @param locale Locale object to allow for localisation
	 * 
	 * @return registration view, refer to /resources/templates/registration.html
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model, Locale locale) {
		super.setCurrentRoute(IControllerConstants.REG_ROUTE_ID);
		super.prepareModel(model);
		model.addAttribute(IControllerConstants.ERRORS_PARAM, false);
		model.addAttribute(USER_FORM, new UserForm());
		return REG_VIEW;	
	}
	
	/**
	 * Request handler for submitting a registration form.
	 * 
	 * @param userForm object capturing the HTML form submitted.
	 * @param bindingResults binds validation results
	 * @param model Model managed by Spring Web MVC
	 * @param locale Locale object to allow for localisation
	 * 
	 * @return bindingResult.hasErrors() -> registration view with errors
	 * @return success -> redirect to login
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute(USER_FORM) @Valid UserForm userForm, BindingResult bindingResult, Locale locale, Model model) {
		super.setCurrentRoute(IControllerConstants.REG_ROUTE_ID);
		super.prepareModel(model);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute(IControllerConstants.ERRORS_PARAM, true);
			return REG_VIEW;
		}
		
		User user = _formToModelFactory.userFormToUser(userForm);
		_userServ.save(user);
		_secServ.autoLogin(user.getUserName(), user.getPassword());
		
		return IControllerConstants.REDIRECT;
	}
	
	/**
	 * Request handler for the login view
	 * 
	 * @param model Model managed by Spring Web MVC
	 * @param locale Locale object to allow for localisation
	 * 
	 * @param error url parameter to indicate if errors exist
	 * @param logout url parameter to indicate if its post logout
	 * 
	 * @return login view, refer to /resources/templates/login.html
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, Locale locale, String error, String logout) {
		super.setCurrentRoute(IControllerConstants.LOGIN_ROUTE_ID);
		super.prepareModel(model);
		return LOGIN_VIEW;
	}
}
