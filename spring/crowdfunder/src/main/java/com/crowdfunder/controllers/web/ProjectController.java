package com.crowdfunder.controllers.web;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crowdfunder.control.forms.EditProjectForm;
import com.crowdfunder.control.forms.PledgeForm;
import com.crowdfunder.control.forms.ProjectForm;
import com.crowdfunder.control.util.DisplayPledges;
import com.crowdfunder.control.util.interfaces.IDisplayPledgeButton;
import com.crowdfunder.controllers.constants.IControllerConstants;
import com.crowdfunder.io.storage.ProjectMultipartFileWrapper;
import com.crowdfunder.models.Project;
import com.crowdfunder.services.interfaces.IProjectService;
import com.crowdfunder.services.interfaces.IStorageService;
import com.crowdfunder.services.interfaces.IUserSecurityService;
import com.crowdfunder.util.calculators.interfaces.IPledgeCalculator;
import com.crowdfunder.util.factories.interfaces.IFormsToModelsFactory;

/**
 ********************************************************************
 * This controller handles requests for the "/projects"
 *
 * This handles WebMVC requests related to the Project domain
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Controller
@RequestMapping("/projects")
public class ProjectController extends ALayoutController {
	
	private static final String ROUTE_ID = "projects";
	
	private static final String PROJECT_VIEW = "project";
	private static final String PROJECTS_VIEW = "projects";
	
	private static final String PROJECT_PARAM = "project";
	private static final String PROJECTS_PARAM = "projects";
	
	private static final String PROJECT_ROUTE_PARAM = "project_route";
	
	private static final String BASE_ROUTE_PARAM = "base_route";
	private static final String BASE_ROUTE = "/projects";
	
	private static final String PROJ_AMOUNT_PLEDGED_PARAM = "proj_amount_pledged";
	
	private static final String PROJ_DAYS_LEFT_PARAM = "proj_days_left";
	
	private static final String USER_DASHB_ROUTE = "dashboard";
	
	private static final String DISPLAY_PLEDGE_BTN_PARAM = "displayPledgeBtn";
	
	private static final String DISPLAY_PLEDGES_PARAM = "displayPledges";
	
	@Autowired
	private IProjectService _projServ;
	
	@Autowired
	private IPledgeCalculator _pledgeCalc;
	
	@Autowired
	private IUserSecurityService _userSecServ;
	
	@Autowired
	private IStorageService _storageServ;
	
	@Autowired
	private IFormsToModelsFactory _formToModelFactory;
	
	@Autowired
	private IDisplayPledgeButton _displayPledgeBtn;

	/**
	 * Handler for HTTP post request to create a project.
	 * 
	 * @param projectForm object representing HTML form for creating a project
	 * @param bindingResults binds validation results
	 * @param model Model managed by Spring Web MVC
	 * @param locale Locale object to allow for localisation
	 * 
	 * @return bindingResult.hasErrors() -> redirect to dashboard with errors
	 * @return success -> redirect to newly created project page
	 */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createProject(@ModelAttribute(IControllerConstants.PROJECT_FORM) final @Valid ProjectForm projectForm, BindingResult bindingResult, final RedirectAttributes redirectAttributes, Locale locale, Model model) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(IControllerConstants.REDIRECT);
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(IControllerConstants.BINDINGRESULT_PACKAGE + IControllerConstants.PROJECT_FORM, bindingResult);
			redirectAttributes.addFlashAttribute(IControllerConstants.PROJECT_FORM, projectForm);
			strBuilder.append(USER_DASHB_ROUTE);
			strBuilder.append(IControllerConstants.QMARK);
			strBuilder.append(IControllerConstants.ERRORS_PARAM);
		} else {
			//Handle creating the project, default image name used.
			Project proj = _formToModelFactory.projectFormToProject(projectForm);
			proj.setUser(_userSecServ.getLoggedInUser());
			_projServ.save(proj);
			
			//Image is wrapped, custom name generated due to project id existing. File is stored
			MultipartFile wrappedFile = new ProjectMultipartFileWrapper(projectForm.getImage(), proj);
			proj.setImageName(wrappedFile.getName());
			_projServ.save(proj);
			_storageServ.store(wrappedFile);
			
			strBuilder.append(IControllerConstants.PROJECT_ROUTE);
			strBuilder.append(IControllerConstants.FW_SLASH);
			strBuilder.append(proj.getProjectId());
		}
		return strBuilder.toString();
	}
	
	@RequestMapping(value="/edit/{projId}", method = RequestMethod.POST)
	public String editProject(@PathVariable Long projId, @ModelAttribute(IControllerConstants.EDIT_PROJ_FORM) final @Valid EditProjectForm editProjectForm, BindingResult bindingResult, final RedirectAttributes redirectAttributes, Locale locale, Model model) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(IControllerConstants.REDIRECT);
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(IControllerConstants.BINDINGRESULT_PACKAGE + IControllerConstants.EDIT_PROJ_FORM, bindingResult);
			redirectAttributes.addFlashAttribute(IControllerConstants.EDIT_PROJ_FORM, editProjectForm);
			strBuilder.append(USER_DASHB_ROUTE);
			strBuilder.append(IControllerConstants.QMARK);
			strBuilder.append(IControllerConstants.ERRORS_PARAM);
			strBuilder.append(IControllerConstants.EQUAL);
			strBuilder.append(IControllerConstants.EDIT);
		} else {
			Project proj = _projServ.getProject(projId);
			proj.setDescription(editProjectForm.getDesc());
			_projServ.save(proj);
			
			strBuilder.append(IControllerConstants.PROJECT_ROUTE);
			strBuilder.append(IControllerConstants.FW_SLASH);
			strBuilder.append(proj.getProjectId());
		}
		return strBuilder.toString();
	}
	
	@RequestMapping(value="/cancel/{projId}", method = RequestMethod.POST)
	public String cancelProject(@PathVariable Long projId, final RedirectAttributes redirectAttributes, Locale locale, Model model) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(IControllerConstants.REDIRECT);
		strBuilder.append(USER_DASHB_ROUTE);
		
		Project proj = _projServ.getProject(projId);
		_projServ.cancelProject(proj);
		
		return strBuilder.toString();
	}

	/**
	 * Handler for get requests on individual projects
	 * 
	 * @param projId project id used to retrieve the relevant project object
	 * @param model Model managed by Spring Web MVC
	 * @param locale Locale object to allow for localisation
	 * 
	 * @return view of individual project, refer to /resources/templates/project.html
	 */
	@RequestMapping(value="/{projId}", method = RequestMethod.GET)
	public String view(@PathVariable Long projId, Locale locale, Model model) {
		super.setCurrentRoute(ROUTE_ID);
		super.prepareModel(model);
		Project proj = _projServ.getProject(projId);
		if(proj.getCancelled()) {
			model.addAttribute(PROJ_AMOUNT_PLEDGED_PARAM, _pledgeCalc.calculateTotalAmount(proj.getPledges()));
		} else {
			model.addAttribute(PROJ_AMOUNT_PLEDGED_PARAM, _pledgeCalc.calculatePledgeAmount(proj.getPledges()));
		}
		model.addAttribute(PROJ_DAYS_LEFT_PARAM, _projServ.getDaysLeft(proj));
		model.addAttribute(PROJECT_PARAM, proj);
		model.addAttribute(DISPLAY_PLEDGE_BTN_PARAM, _displayPledgeBtn);
		model.addAttribute(DISPLAY_PLEDGES_PARAM, new DisplayPledges());
		
		if(!model.containsAttribute(IControllerConstants.PLEDGE_FORM)) {
			model.addAttribute(IControllerConstants.PLEDGE_FORM, new PledgeForm());
		}
		
		return PROJECT_VIEW;
	}
	
	/**
	 * Paging handler for viewing all projects
	 * 
	 * @param model Model managed by Spring Web MVC
	 * @param locale Locale object to allow for localisation
	 * @param pageable Pageable parameters that allow for page selection and sorting.
	 * 
	 * @return paging, sortable projects view, refer to /resources/templates/projects.html
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String view(Locale locale, Model model, Pageable pageable) {
		super.setCurrentRoute(ROUTE_ID);
		super.prepareModel(model);
		model.addAttribute(PROJECTS_PARAM, _projServ.getAllProjects(pageable));
		model.addAttribute(BASE_ROUTE_PARAM, BASE_ROUTE);
		model.addAttribute(PROJECT_ROUTE_PARAM, IControllerConstants.PROJECT_ROUTE);
		return PROJECTS_VIEW;
	}
}
