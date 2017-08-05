package com.voting_app.repositories.row.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.voting_app.repositories.row.models.CandidateRow;
import com.voting_app.repositories.schema.CandidateTableSchema;

/**
 ********************************************************************
 * RowMapper object for CandidateRow objects
 * 
 * This is used by jdbc to convert a cursor row into an object
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateRowMapper implements RowMapper<CandidateRow> {

	@Override
	public CandidateRow mapRow(ResultSet rs, int rowNum) throws SQLException {
		CandidateRow candidateRow = new CandidateRow();
		candidateRow.setId(rs.getInt(CandidateTableSchema.CANDIDATE_ID_COL));
		candidateRow.setConstituencyId(rs.getInt(CandidateTableSchema.CONSTITUENCY_ID_COL));
		candidateRow.setPoliticalPartyId(rs.getInt(CandidateTableSchema.POLITICAL_PARTY_ID_COL));
		candidateRow.setName(rs.getString(CandidateTableSchema.CANDIDATE_NAME_COL));
		return candidateRow;
	}

}
