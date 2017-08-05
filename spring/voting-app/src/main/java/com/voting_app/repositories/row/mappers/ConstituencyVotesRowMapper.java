package com.voting_app.repositories.row.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.voting_app.repositories.row.models.ConstituencyVotesRow;
import com.voting_app.repositories.schema.ConstituencyVotesTableSchema;

/**
 ********************************************************************
 * RowMapper object for ConstituencyVotesRow objects
 * 
 * This is used by jdbc to convert a cursor row into an object
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ConstituencyVotesRowMapper implements RowMapper<ConstituencyVotesRow> {

	@Override
	public ConstituencyVotesRow mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConstituencyVotesRow constituencyRow = new ConstituencyVotesRow();
		constituencyRow.setId(rs.getInt(ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_ID_COL));
		constituencyRow.setConstituencyId(rs.getInt(ConstituencyVotesTableSchema.CONSTITUENCY_ID_COL));
		constituencyRow.setValidVotes(rs.getInt(ConstituencyVotesTableSchema.VALID_VOTES_COL));
		constituencyRow.setInvalidVotes(rs.getInt(ConstituencyVotesTableSchema.INVALID_VOTES_COL));
		return constituencyRow;
	}

}
