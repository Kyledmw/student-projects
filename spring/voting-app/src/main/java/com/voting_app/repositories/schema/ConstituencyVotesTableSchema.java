package com.voting_app.repositories.schema;

/**
 ********************************************************************
 * Interface containing constants related to the ConstituencyVotesTable Schema
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ConstituencyVotesTableSchema {
	
	String CONSTITUENCY_VOTES_TABLE = "constituency_votes";
	
	String CONSTITUENCY_VOTES_ID_COL = "constituency_votes_id";
	String CONSTITUENCY_ID_COL = "constituency_id";
	String VALID_VOTES_COL = "valid_votes";
	String INVALID_VOTES_COL = "invalid_votes";
}
