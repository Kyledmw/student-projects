package com.voting_app.repositories.schema;

/**
 ********************************************************************
 * Interface containing constants related to the CandidateTable Schema
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface CandidateTableSchema {

	String CANDIDATE_TABLE = "candidates";
	
	String CANDIDATE_ID_COL = "candidate_id";
	String CANDIDATE_NAME_COL = "candidate_name";
	String POLITICAL_PARTY_ID_COL = "political_party_id";
	String CONSTITUENCY_ID_COL = "constituency_id";
}
