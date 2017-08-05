package com.crowdfunder.controllers.constants;

/**
 ********************************************************************
 * Interface containing constants used within the control layer.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IControllerConstants {
	
	String REG_ROUTE_ID = "registration";
	String LOGIN_ROUTE_ID = "login";
	String USER_ROUTE_ID = "user_page";

	String REDIRECT = "redirect:/";
	String QMARK = "?";
	String FW_SLASH = "/";
	String EQUAL = "=";
	
	String EDIT = "edit";
	
	String PROJECT_FORM = "projectForm";
	String EDIT_PROJ_FORM = "editProjForm";
	String PLEDGE_FORM = "pledgeForm";
	
	String BINDING_PARAM = "binding";
	
	String ERRORS_PARAM = "errors";
	
	String BINDINGRESULT_PACKAGE = "org.springframework.validation.BindingResult.";

	String PROJECT_ROUTE = "projects";
}
