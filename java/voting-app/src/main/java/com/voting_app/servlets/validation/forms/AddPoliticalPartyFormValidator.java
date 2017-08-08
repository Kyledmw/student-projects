package com.voting_app.servlets.validation.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.PoliticalParty;

/**
 ********************************************************************
 * Form Validator for the add political party form
 * <br>
 * <br>
 * @implements {@link IFormValidator}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class AddPoliticalPartyFormValidator implements IFormValidator {
	
	private static final String POLITICAL_PARTY_NAME = "p_name";

	public List<String> validate(IVotePollDAO dao, HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		String name = request.getParameter(POLITICAL_PARTY_NAME);
		
		for(PoliticalParty cur: dao.getPoliticalPartyDAO().getPoliticalParties()) {
			if(cur.getName().equals(name)) {
				errors.add(IFormValidatorConstants.UNIQUE_NAME);
				break;
			}
		}
		return errors;
	}

}
