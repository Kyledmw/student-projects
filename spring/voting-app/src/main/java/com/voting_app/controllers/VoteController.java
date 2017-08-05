package com.voting_app.controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voting_app.controllers.constants.IControllerConstants;
import com.voting_app.controllers.forms.VoteForm;
import com.voting_app.models.Candidate;
import com.voting_app.models.CandidateVote;
import com.voting_app.models.Constituency;
import com.voting_app.models.ConstituencyVotes;
import com.voting_app.services.interfaces.ICandidateService;
import com.voting_app.services.interfaces.ICandidateVotesService;
import com.voting_app.services.interfaces.IConstituencyService;
import com.voting_app.services.interfaces.IConstituencyVotesService;
import com.voting_app.util.sanitizer.ICandidateVoteListSanitizer;

/**
 ********************************************************************
 * This controller handles requests for the "/{pattern}/vote"
 *
 * This handles the vote form for a constituency and handles the form request
 * 
 * The {pattern} path variable represents the constituency id
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class VoteController {
	
	private static final String CANDIDATES = "candidates";
	
	private static final String VOTE_FORM = "voteForm";
	
	private static final String VOTE_VIEW = "vote";

	@Autowired
	private ICandidateService _candidateService;
	
	@Autowired
	private IConstituencyService _constService;
	
	@Autowired
	private IConstituencyVotesService _constVotesService;
	
	@Autowired
	private ICandidateVotesService _candVotesService;
	
	@Autowired
	private ICandidateVoteListSanitizer _candSanitizer;
	
	/**
	 * Prepares the vote view for the given constituency
	 * 
	 * @param pattern constituency id
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return VOTE_VIEW;
	 */
	@RequestMapping(value="/{pattern}/vote", method = RequestMethod.GET)
	public String view(@PathVariable String pattern, Locale locale, Model model) {
		prepareVoteView(pattern, model);
		model.addAttribute("voteForm", new VoteForm());		
		return VOTE_VIEW;
	}
	
	/**
	 * Request that handles a vote form post
	 * Goes back to the vote view with error/success messages
	 * 
	 * @param pattern constituency id
	 * @param voteForm VoteForm object validated and containing user votes
	 * @param bindingResult object containing results from the hibernate validation
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return VOTE_VIEW
	 */
	@RequestMapping(value="/{pattern}/vote", method = RequestMethod.POST)
	public String vote(@PathVariable String pattern, @ModelAttribute(VOTE_FORM) @Valid VoteForm voteForm, BindingResult bindingResult, Locale locale, Model model) {
		prepareVoteView(pattern, model);
		ConstituencyVotes constVotes = _constVotesService.getVotesForConstiuency(getConstitorPattern(pattern));

		if(bindingResult.hasErrors()) {
			constVotes.incrementInvalidVotes();
			model.addAttribute(IControllerConstants.ERRORS, true);
		} else {
			constVotes.incrementValidVotes();
			List<CandidateVote> sanitizedList = _candSanitizer.sanitizeList(voteForm.getVotes());
			_candVotesService.addVoteList(sanitizedList);
			model.addAttribute(IControllerConstants.SUCCESS, true);
		}
		_constVotesService.save(constVotes);
		model.addAttribute(VOTE_FORM, voteForm);
		return VOTE_VIEW;
	}
	
	/**
	 * Adds all the relevant collection data for the view
	 * Default some attributes e.g. error/success
	 * 
	 * @param pattern path variable
	 * @param model model object used for passing parameters into the view
	 */
	private void prepareVoteView(String pattern, Model model) {
		Constituency constituency = getConstitorPattern(pattern);
		List<Candidate> candidates = _candidateService.getCandidatesForConstituency(constituency);
		model.addAttribute(IControllerConstants.CONSTITUENCY, constituency);
		model.addAttribute(CANDIDATES, candidates);
		model.addAttribute(IControllerConstants.SUCCESS, false);
		model.addAttribute(IControllerConstants.ERRORS, false);
	}
	
	/**
	 * Gets the Constituency from the path variable pattern
	 * 
	 * @param pattern path variable containing constituency id
	 * @return Constituency constituency for the constituency id 
	 */
	private Constituency getConstitorPattern(String pattern) {
		return _constService.get(Integer.parseInt(pattern));
	}
}
