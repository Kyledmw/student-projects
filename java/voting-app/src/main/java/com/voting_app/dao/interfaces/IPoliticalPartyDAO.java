package com.voting_app.dao.interfaces;

import java.util.List;

import com.voting_app.models.CandidateVotes;
import com.voting_app.models.PoliticalParty;

/**
 ********************************************************************
 * Interface for a DAO of {@link CandidateVotes} objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IPoliticalPartyDAO {

	/**
	 * Add a political party to the DAO
	 * 
	 * @param id unique id for the political party
	 * @param name name of the political party
	 * @return created {@link PoliticalParty} object
	 */
	PoliticalParty addPoliticalParty(int id, String name);

	/**
	 * Add a political party to the DAO
	 * 
	 * @param name name of the political party
	 * @return created {@link PoliticalParty} object
	 */
	PoliticalParty addPoliticalParty(String name);
	
	/**
	 * Remove a political party with the given id
	 * 
	 * @param id remove the party for the given id
	 */
	void removePoliticalParty(int id);
	
	/**
	 * Retrieve a political party for the given id
	 * 
	 * @param id unique id for political parties
	 * @return {@link PoliticalParty} for the given id
	 */
	PoliticalParty getPoliticalParty(int id);
	
	/**
	 * Retrieve a list of all {@link PoliticalParty} objects
	 * 
	 * @return {link List} of {@link PoliticalParty}
	 */
	List<PoliticalParty> getPoliticalParties();
}
