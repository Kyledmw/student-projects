package com.voting_app.controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voting_app.controllers.constants.IControllerConstants;
import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;
import com.voting_app.models.ConstituencyVotes;
import com.voting_app.services.interfaces.ICandidateService;
import com.voting_app.services.interfaces.ICandidateVotesService;
import com.voting_app.services.interfaces.IConstituencyService;
import com.voting_app.services.interfaces.IConstituencyVotesService;
import com.voting_app.util.calculators.VoteCalculator;

/**
 ********************************************************************
 * This controller handles requests for /{pattern}/results route
 * 
 * Returns the vote results for the selected constituency determined off the {pattern} path variable
 * 
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class ResultsController {

	private static final String VOTE_CALCULATOR = "voteCalculator";
	private static final String CONSTITUENCY_VOTES = "constituencyVotes";
	private static final String CANDIDATE_VOTES_LIST = "candidateVotesList";
	private static final String TOTAL_CANDIDATES = "totalCandidates";
	
	private static final String RESULTS_VIEW = "results";
	
	@Autowired
	private ICandidateService _candidateService;
	
	@Autowired
	private IConstituencyService _constService;
	
	@Autowired
	private IConstituencyVotesService _constVotesService;
	
	@Autowired
	private ICandidateVotesService _candVotesService; 

	/**
	 * Prepares the results view
	 * 
	 * Providing it with constituncy vote and candidate vote objects
	 * 
	 * @param pattern path variable containing the selected constituency id
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return RESULTS_VIEW
	 */
	@RequestMapping(value="/{pattern}/results", method = RequestMethod.GET)
	public String view(@PathVariable String pattern, Locale locale, Model model) {
		Constituency constituency = _constService.get(Integer.parseInt(pattern));
		ConstituencyVotes cVotes = _constVotesService.getVotesForConstiuency(constituency);
		List<Candidate> cList = _candidateService.getCandidatesForConstituency(constituency);
		model.addAttribute(IControllerConstants.CONSTITUENCY, constituency);
		model.addAttribute(VOTE_CALCULATOR, new VoteCalculator());
		model.addAttribute(CONSTITUENCY_VOTES , cVotes);
		model.addAttribute(CANDIDATE_VOTES_LIST, _candVotesService.getVotesForCandidates(cList));
		model.addAttribute(TOTAL_CANDIDATES, cList.size());
		return RESULTS_VIEW;
	}
}
