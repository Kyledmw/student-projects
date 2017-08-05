package com.voting_app.services.interfaces;

import java.util.List;

import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;

/**
 ********************************************************************
 * Generic service interface for a Candidate Repository
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ICandidateService extends IGenericService<Candidate> {

	/**
	 * Retrieve candidate objects as a list for the given constituency object
	 * 
	 * @param constituency Constituency
	 * @return List<Canddiate>
	 */
	List<Candidate> getCandidatesForConstituency(Constituency constituency);
}
