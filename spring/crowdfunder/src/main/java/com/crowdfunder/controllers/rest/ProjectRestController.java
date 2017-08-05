package com.crowdfunder.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crowdfunder.models.Project;
import com.crowdfunder.services.interfaces.IProjectService;

/**
 ********************************************************************
 * Controller that handles RESTful HTTP Requests for the Project domain
 * 
 * Resources exist under the /api/projects route.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {
	
	@Autowired
	private IProjectService _projServ;
	
	/**
	 * RESTful resource for Pageable Projects
	 * 
	 * @param pageable Pageable parameters that allow for page selection and sorting.
	 * @return Page<Project> Page resource for the given parameters. 
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET, headers = "Accept=application/json")
	public Page<Project> getProject(Pageable pageable) {
		return _projServ.getAllProjects(pageable);
	}

	/**
	 * RESTful resource for a Project
	 * 
	 * @param projId Id to retrieve project
	 * @return JSON representation of the Project object
	 */
	@RequestMapping(value = "/{projId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Project getProject(@PathVariable Long projId) {
		return _projServ.getProject(projId);
	}

	/**
	 * RESTful resources for all Projects
	 * 
	 * @return List<Project> JSON list of projects
	 */
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Project> getProjects() {
		return _projServ.getAllProjects();
	}
}
