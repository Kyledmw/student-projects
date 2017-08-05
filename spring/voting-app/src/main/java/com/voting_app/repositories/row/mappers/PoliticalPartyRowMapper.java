package com.voting_app.repositories.row.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.voting_app.models.PoliticalParty;
import com.voting_app.repositories.schema.PoliticalPartyTableSchema;

/**
 ********************************************************************
 * RowMapper object for PoliticalPartyRow objects
 * 
 * This is used by jdbc to convert a cursor row into an object
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class PoliticalPartyRowMapper implements RowMapper<PoliticalParty> {

	@Override
	public PoliticalParty mapRow(ResultSet rs, int rowNum) throws SQLException {
		PoliticalParty pparty = new PoliticalParty();
		pparty.setId(rs.getInt(PoliticalPartyTableSchema.POLITICAL_PARTY_ID_COL));
		pparty.setName(rs.getString(PoliticalPartyTableSchema.POLITICAL_PARTY_NAME_COL));
		return pparty;
	}

}
