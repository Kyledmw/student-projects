package com.voting_app.servlets.validation.forms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.Candidate;

/**
 ********************************************************************
 * Form Validator for the voting form
 * <br>
 * <br>
 * @implements {@link IFormValidator}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class VoteFormValidator implements IFormValidator {

	public List<String> validate(IVotePollDAO dao, HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		List<Integer> votes = new ArrayList<Integer>();
		for(Candidate cur: dao.getCandidateDAO().getCandidates()) {
			String rankStr = request.getParameter("" + cur.getId());
			if(rankStr != null && !rankStr.isEmpty()) {
				Integer rank = Integer.parseInt(rankStr);
				votes.add(rank);
			}
		}
		Collections.sort(votes);
		for(int i = 0; i < votes.size(); i++) {
			int rank = i + 1;
			if(rank != votes.get(i)) {
				errors.add(IFormValidatorConstants.INVALID_VOTE);
				errors.add(IFormValidatorConstants.SEQUENTIAL_VOTES);
				break;
			}
		}
		return errors;
	}

}
