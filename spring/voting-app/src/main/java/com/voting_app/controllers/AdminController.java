package com.voting_app.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voting_app.controllers.constants.IControllerConstants;
import com.voting_app.controllers.forms.CandidateForm;
import com.voting_app.controllers.forms.ConstituencyForm;
import com.voting_app.controllers.forms.DeleteCandidateForm;
import com.voting_app.controllers.forms.DeleteConstituencyForm;
import com.voting_app.controllers.forms.DeletePoliticalPartyForm;
import com.voting_app.controllers.forms.PoliticalPartyForm;
import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;
import com.voting_app.models.PoliticalParty;
import com.voting_app.services.interfaces.ICandidateService;
import com.voting_app.services.interfaces.IConstituencyService;
import com.voting_app.services.interfaces.IDeleteService;
import com.voting_app.services.interfaces.IPoliticalPartyService;

/**
 ********************************************************************
 * This controller handles all requests for the "/admin" route
 * 
 * Requests comprise of creating and deleting model objects from their repositories
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
public class AdminController {
	
	private final static String ADMIN_VIEW = "admin";
	
	private final static String CONSTITUENCY_FORM = "constituencyForm";
	private final static String PPARTY_FORM = "ppartyForm";
	private final static String CANDIDATE_FORM = "candidateForm";
	
	private final static String DEL_CANDIDATE_FORM = "delCandidateForm";
	private final static String DEL_PPARTY_FORM = "delPoliticalPartyForm";
	private final static String DEL_CONSTIT_FORM = "delConstituencyForm";
	
	private final static String POLITICAL_PARTIES = "politicalParties";
	private static final String CANDIDATES = "candidates";
	
	@Autowired
	private IConstituencyService _constService;
	
	@Autowired
	private IPoliticalPartyService _ppartyService;
	
	@Autowired
	private ICandidateService _candService;
	
	@Autowired
	private IDeleteService _delService;

	/**
	 * Prepares the admin view.
	 * Passes in new form objects and model collections
	 * 
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return ADMIN_VIEW admin.html
	 */
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String view(Locale locale, Model model) {
		prepareAdminView(model);
		model.addAttribute(IControllerConstants.SUCCESS, false);
		model.addAttribute(IControllerConstants.ERRORS, false);

		model.addAttribute(CONSTITUENCY_FORM, new ConstituencyForm());
		model.addAttribute(PPARTY_FORM, new PoliticalPartyForm());
		model.addAttribute(CANDIDATE_FORM, new CandidateForm());
		model.addAttribute(DEL_CANDIDATE_FORM, new DeleteCandidateForm());
		model.addAttribute(DEL_PPARTY_FORM, new DeletePoliticalPartyForm());
		model.addAttribute(DEL_CONSTIT_FORM, new DeleteConstituencyForm());
		
		return ADMIN_VIEW;
	}

	/**
	 * Create constituency post request, returns to ADMIN_VIEW with errors/success
	 * 
	 * @param constForm ConstituencyForm validated & containing user entered details
	 * @param bindingResult object containing results from the hibernate validation
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return ADMIN_VIEW admin.html
	 */
	@RequestMapping(value="/admin/createConstituency", method = RequestMethod.POST)
	public String createConst(@ModelAttribute(CONSTITUENCY_FORM) @Valid ConstituencyForm constForm, BindingResult bindingResult, Locale locale, Model model) {
		model.addAttribute(IControllerConstants.SUCCESS, false);
		model.addAttribute(IControllerConstants.ERRORS, false);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute(IControllerConstants.ERRORS, true);
		} else {
			Constituency constit = new Constituency();
			constit.setName(constForm.getName());
			_constService.save(constit);
			model.addAttribute(IControllerConstants.SUCCESS, true);
		}
		
		prepareAdminView(model);
		
		model.addAttribute(PPARTY_FORM, new PoliticalPartyForm());
		model.addAttribute(CANDIDATE_FORM, new CandidateForm());		
		model.addAttribute(DEL_CANDIDATE_FORM, new DeleteCandidateForm());
		model.addAttribute(DEL_PPARTY_FORM, new DeletePoliticalPartyForm());
		model.addAttribute(DEL_CONSTIT_FORM, new DeleteConstituencyForm());
		
		return ADMIN_VIEW;
	}

	/**
	 * Create politicalparty post request, returns to ADMIN_VIEW with errors/success
	 * 
	 * @param ppartyForm PoliticalPartyForm validated & containing user entered details
	 * @param bindingResult object containing results from the hibernate validation
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return ADMIN_VIEW admin.html
	 */
	@RequestMapping(value="/admin/createPoliticalParty", method = RequestMethod.POST)
	public String createPParty(@ModelAttribute(PPARTY_FORM) @Valid PoliticalPartyForm ppartyForm, BindingResult bindingResult, Locale locale, Model model) {
		model.addAttribute(IControllerConstants.SUCCESS, false);
		model.addAttribute(IControllerConstants.ERRORS, false);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute(IControllerConstants.ERRORS, true);
		} else {
			PoliticalParty pparty = new PoliticalParty();
			pparty.setName(ppartyForm.getName());
			_ppartyService.save(pparty);
			model.addAttribute(IControllerConstants.SUCCESS, true);
		}
		
		prepareAdminView(model);

		model.addAttribute(CONSTITUENCY_FORM, new ConstituencyForm());
		model.addAttribute(CANDIDATE_FORM, new CandidateForm());
		model.addAttribute(DEL_CANDIDATE_FORM, new DeleteCandidateForm());
		model.addAttribute(DEL_PPARTY_FORM, new DeletePoliticalPartyForm());
		model.addAttribute(DEL_CONSTIT_FORM, new DeleteConstituencyForm());
		
		return ADMIN_VIEW;
	}

	/**
	 * Create candidate post request, returns to ADMIN_VIEW with errors/success
	 * 
	 * @param candForm CandidateForm validated & containing user entered details
	 * @param bindingResult object containing results from the hibernate validation
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return ADMIN_VIEW admin.html
	 */
	@RequestMapping(value="/admin/createCandidate", method = RequestMethod.POST)
	public String createCandidate(@ModelAttribute(CANDIDATE_FORM) @Valid CandidateForm candForm, BindingResult bindingResult, Locale locale, Model model) {
		model.addAttribute(IControllerConstants.SUCCESS, false);
		model.addAttribute(IControllerConstants.ERRORS, false);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute(IControllerConstants.ERRORS, true);
		} else {
			Constituency constit = new Constituency();
			constit.setId(candForm.getConstituencyId());
			PoliticalParty pparty = new PoliticalParty();
			pparty.setId(candForm.getPoliticalPartyId());
			Candidate cand = new Candidate();
			cand.setName(candForm.getName());
			cand.setConstituency(constit);
			cand.setPoliticalParty(pparty);
			_candService.save(cand);
			model.addAttribute(IControllerConstants.SUCCESS, true);
		}
		
		prepareAdminView(model);
		
		model.addAttribute(CONSTITUENCY_FORM, new ConstituencyForm());
		model.addAttribute(PPARTY_FORM, new PoliticalPartyForm());
		model.addAttribute(DEL_CANDIDATE_FORM, new DeleteCandidateForm());
		model.addAttribute(DEL_PPARTY_FORM, new DeletePoliticalPartyForm());
		model.addAttribute(DEL_CONSTIT_FORM, new DeleteConstituencyForm());
		
		return ADMIN_VIEW;
	}

	/**
	 * Create delete candidate post request, returns to ADMIN_VIEW with errors/success
	 * 
	 * @param delCandidateForm DeleteCandidateForm validated & containing the candidate id to be deleted
	 * @param bindingResult object containing results from the hibernate validation
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return ADMIN_VIEW admin.html
	 */
	@RequestMapping(value="/admin/deleteCandidate", method = RequestMethod.POST)
	public String deleteCandidate(@ModelAttribute(DEL_CANDIDATE_FORM) @Valid DeleteCandidateForm delCandidateForm, BindingResult bindingResult, Locale locale, Model model) {
		model.addAttribute(IControllerConstants.SUCCESS, false);
		model.addAttribute(IControllerConstants.ERRORS, false);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute(IControllerConstants.ERRORS, true);
		} else {
			Candidate cand = new Candidate();
			cand.setId(delCandidateForm.getId());
			_delService.deleteCandidate(cand);
			model.addAttribute(IControllerConstants.SUCCESS, true);
		}
		
		prepareAdminView(model);
		
		model.addAttribute(CONSTITUENCY_FORM, new ConstituencyForm());
		model.addAttribute(PPARTY_FORM, new PoliticalPartyForm());
		model.addAttribute(CANDIDATE_FORM, new CandidateForm());
		model.addAttribute(DEL_PPARTY_FORM, new DeletePoliticalPartyForm());
		model.addAttribute(DEL_CONSTIT_FORM, new DeleteConstituencyForm());
		
		return ADMIN_VIEW;
	}

	/**
	 * Create delete political party post request, returns to ADMIN_VIEW with errors/success
	 * 
	 * @param delPPartyForm DeletePoliticalPartyForm validated & containing the political party id to be deleted
	 * @param bindingResult object containing results from the hibernate validation
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return ADMIN_VIEW admin.html
	 */
	@RequestMapping(value="/admin/deletePoliticalParty", method = RequestMethod.POST)
	public String deletePParty(@ModelAttribute(DEL_PPARTY_FORM) @Valid  DeletePoliticalPartyForm delPPartyForm, BindingResult bindingResult, Locale locale, Model model) {
		model.addAttribute(IControllerConstants.SUCCESS, false);
		model.addAttribute(IControllerConstants.ERRORS, false);

		if(bindingResult.hasErrors()) {
			model.addAttribute(IControllerConstants.ERRORS, true);
		} else {
			PoliticalParty pparty = new PoliticalParty();
			pparty.setId(delPPartyForm.getId());
			_delService.deletePoliticalParty(pparty);
			model.addAttribute(IControllerConstants.SUCCESS, true);
		}
		
		prepareAdminView(model);

		model.addAttribute(CONSTITUENCY_FORM, new ConstituencyForm());
		model.addAttribute(PPARTY_FORM, new PoliticalPartyForm());
		model.addAttribute(CANDIDATE_FORM, new CandidateForm());
		model.addAttribute(DEL_CANDIDATE_FORM, new DeleteCandidateForm());
		model.addAttribute(DEL_CONSTIT_FORM, new DeleteConstituencyForm());
		
		return ADMIN_VIEW;
	}

	/**
	 * Create delete constituency post request, returns to ADMIN_VIEW with errors/success
	 * 
	 * @param delConstituencyForm DeleteConstituencyForm validated & containing the constituency id to be deleted
	 * @param bindingResult object containing results from the hibernate validation
	 * @param locale locale object used to determine localisation
	 * @param model model object used for passing parameters into the view
	 * @return ADMIN_VIEW admin.html
	 */
	@RequestMapping(value="/admin/deleteConstituency", method = RequestMethod.POST)
	public String deleteConstituency(@ModelAttribute(DEL_CONSTIT_FORM) @Valid DeleteConstituencyForm delConstituency, BindingResult bindingResult, Locale locale, Model model) {
		model.addAttribute(IControllerConstants.SUCCESS, false);
		model.addAttribute(IControllerConstants.ERRORS, false);

		if(bindingResult.hasErrors()) {
			model.addAttribute(IControllerConstants.ERRORS, true);
		} else {
			Constituency constit = new Constituency();
			constit.setId(delConstituency.getId());
			_delService.deleteConstituency(constit);
			model.addAttribute(IControllerConstants.SUCCESS, true);
		}

		prepareAdminView(model);

		model.addAttribute(CONSTITUENCY_FORM, new ConstituencyForm());
		model.addAttribute(PPARTY_FORM, new PoliticalPartyForm());
		model.addAttribute(CANDIDATE_FORM, new CandidateForm());
		model.addAttribute(DEL_CANDIDATE_FORM, new DeleteCandidateForm());
		model.addAttribute(DEL_PPARTY_FORM, new DeletePoliticalPartyForm());
		
		return ADMIN_VIEW;
	}
	
	/**
	 * Adds model collections to the model object
	 * @param model Model object used by requests
	 */
	private void prepareAdminView(Model model) {
		model.addAttribute(IControllerConstants.CONSTITUENCIES, _constService.getAll());
		model.addAttribute(POLITICAL_PARTIES, _ppartyService.getAll());
		model.addAttribute(CANDIDATES, _candService.getAll());
	}
}
