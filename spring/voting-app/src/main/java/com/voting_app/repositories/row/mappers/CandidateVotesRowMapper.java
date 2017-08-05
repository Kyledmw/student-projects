package com.voting_app.repositories.row.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.voting_app.repositories.row.models.CandidateVotesRow;
import com.voting_app.repositories.schema.CandidateVotesTableSchema;

/**
 ********************************************************************
 * RowMapper object for CandidateVotesRow objects
 * 
 * This is used by jdbc to convert a cursor row into an object
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateVotesRowMapper implements RowMapper<CandidateVotesRow> {

	@Override
	public CandidateVotesRow mapRow(ResultSet rs, int rowNum) throws SQLException {
		CandidateVotesRow candidateVoteRow = new CandidateVotesRow();
		candidateVoteRow.setId(rs.getInt(CandidateVotesTableSchema.CANDIDATE_VOTES_ID_COL));
		candidateVoteRow.setCandidateId(rs.getInt(CandidateVotesTableSchema.CANDIDATE_ID_COL));
		candidateVoteRow.setVoteRank(rs.getInt(CandidateVotesTableSchema.VOTE_RANK_COL));
		return candidateVoteRow;
	}

}
