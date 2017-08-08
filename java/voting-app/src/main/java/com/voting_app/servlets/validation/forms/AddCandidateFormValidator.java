package com.voting_app.servlets.validation.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.Candidate;

/**
 ********************************************************************
 * Form Validator for the add candidates form
 * <br>
 * <br>
 * @implements {@link IFormValidator}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class AddCandidateFormValidator implements IFormValidator {
	
	private static final String CANDIDATE_NAME = "c_name";

	public List<String> validate(IVotePollDAO dao, HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		String name = request.getParameter(CANDIDATE_NAME);
		
		for(Candidate cur: dao.getCandidateDAO().getCandidates()) {
			if(cur.getName().equals(name)) {
				errors.add(IFormValidatorConstants.UNIQUE_NAME);
			}
		}
		return errors;
	}

}
