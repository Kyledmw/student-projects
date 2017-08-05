package com.crowdfunder.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

import com.crowdfunder.constants.ISharedConstants;
import com.crowdfunder.services.interfaces.ISecurityService;
import com.crowdfunder.services.interfaces.IUserService;

/**
 ********************************************************************
 * Abstract superclass for WebMVC controllers.
 * Handles logic thats shared between controllers such as setting up the model for layout.html
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ALayoutController {
	
	private static final String CUR_ROUTE_PARAM = "current_route";
	
	private static final String LOCALES_PARAM = "locales";
	
	private static final String USER_PARAM = "user";
	
	private static final String LOGGED_IN_PARAM = "loggedIn";
	
	@Autowired
	private ISecurityService _secServ;
	
	@Autowired
	private IUserService _userServ;
	
	@Value("${app.supported_locales}")
	private String[] _supportedLocales;
	
	private String _currentRoute;

	protected ALayoutController() {}
	
	/**
	 * Set the current route in model to indicate the active view on the navbar.
	 * 
	 * @param currentRoute String that acts as an identifier for the current route
	 */
	protected void setCurrentRoute(String currentRoute) {
		_currentRoute = currentRoute;
	}
	
	/**
	 * Prepare model for shared attributes
	 * 
	 * @param model Model managed by WebMVC
	 * @return Updated Model
	 */
	protected Model prepareModel(Model model) {
		String name = _secServ.findLoggedInUsername();
		model.addAttribute(CUR_ROUTE_PARAM, _currentRoute);
		model.addAttribute(LOCALES_PARAM, _supportedLocales);
		model.addAttribute(LOGGED_IN_PARAM, (!name.equals(ISharedConstants.ANON_USER)));
		model.addAttribute(USER_PARAM, _userServ.findByUsername(name));
		return model;
	}
	
}
