package com.crowdfunder.controllers.web;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 ********************************************************************
 * This controller handles requests for the "/"
 *
 * This handles getting the index page and its relevant data.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class IndexController extends ALayoutController {
	
	private static final String ROUTE_ID = "index";
	
	private static final String INDEX_VIEW = "index";

	/**
	 * Prepares the home page
	 * 
	 * @param model Model managed by Spring Web MVC
	 * @param locale Locale object to allow for localisation
	 * 
	 * @return home page, refer to /resources/templates/index.html
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String view(Locale locale, Model model) {
		super.setCurrentRoute(ROUTE_ID);
		super.prepareModel(model);
		return INDEX_VIEW;
	}

}
