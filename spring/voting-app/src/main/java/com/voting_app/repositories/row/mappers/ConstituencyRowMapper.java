package com.voting_app.repositories.row.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.voting_app.models.Constituency;
import com.voting_app.repositories.schema.ConstituencyTableSchema;

/**
 ********************************************************************
 * RowMapper object for Constituency objects
 * 
 * This is used by jdbc to convert a cursor row into an object
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ConstituencyRowMapper implements RowMapper<Constituency> {

	@Override
	public Constituency mapRow(ResultSet rs, int rowNum) throws SQLException {
		Constituency constituency = new Constituency();
		constituency.setId(rs.getInt(ConstituencyTableSchema.CONSTITUENCY_ID_COL));
		constituency.setName(rs.getString(ConstituencyTableSchema.CONSTITUENCY_NAME_COL));
		return constituency;
	}

}
