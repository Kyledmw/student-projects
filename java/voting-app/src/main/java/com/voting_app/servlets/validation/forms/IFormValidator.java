package com.voting_app.servlets.validation.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.voting_app.dao.interfaces.IVotePollDAO;

/**
 ********************************************************************
 * Interface for a VotePoll form validator
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IFormValidator {

	/**
	 * Validate the given form request
	 * 
	 * @param dao {@link IVotePollDAO} DAO for checking form data is valid
	 * @param request {@link HttpServletReqeust} object to get form input
	 * @return {@link List} of errors stored as Strings
	 */
	List<String> validate(IVotePollDAO dao, HttpServletRequest request);
}
