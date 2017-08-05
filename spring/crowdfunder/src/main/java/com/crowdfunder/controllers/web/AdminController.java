package com.crowdfunder.controllers.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdfunder.controllers.constants.IControllerConstants;
import com.crowdfunder.services.interfaces.IProjectService;

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
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IProjectService _projService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String updateProjects(Locale locale, Model model) {
		_projService.updateProjectsStatus();
		return IControllerConstants.REDIRECT;
	}
}
