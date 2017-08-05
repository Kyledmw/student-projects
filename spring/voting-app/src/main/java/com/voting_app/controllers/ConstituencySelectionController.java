package com.voting_app.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.voting_app.controllers.constants.IControllerConstants;
import com.voting_app.services.interfaces.IConstituencyService;

/**
 ********************************************************************
 * This controller handles requests for the "/" route
 * 
 * Returns the initial/index view which gives a list of constituencies to select from
 * 
 * The Route parameter allows a user to swap the routing to the results page
 * 
 * E.G: /?route=results will make the constituency route to its relevant results page
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class ConstituencySelectionController {
	
	private static final String CONSTITUENCY_SELECTION_VIEW = "constituency_selection";
	
	private static final String ROUTE_PARAM = "route";
	
	private static final String VOTE_ROUTE = "vote";
	private static final String RESULTS_ROUTE = "results";
	
	@Autowired
	private IConstituencyService _constService;

	/**
	 * Prepares the constituency selection view
	 * These route to the relevant vote/results page based off the route parameter
	 * 
	 * @param route route parameter to determine constituency routing
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return CONSTITUENCY_SELECTION_VIEW
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewToVote(@RequestParam(value = ROUTE_PARAM, required=false) String route, Locale locale, Model model) {
		if(VOTE_ROUTE.equals(route) || RESULTS_ROUTE.equals(route)) {
			model.addAttribute(ROUTE_PARAM, route);
		} else {
			model.addAttribute(ROUTE_PARAM, VOTE_ROUTE);
		}
		model.addAttribute(IControllerConstants.CONSTITUENCIES,_constService.getAll());
		
		return CONSTITUENCY_SELECTION_VIEW;
	}
}
