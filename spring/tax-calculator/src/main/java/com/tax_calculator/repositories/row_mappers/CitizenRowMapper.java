package com.tax_calculator.repositories.row_mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tax_calculator.models.Citizen;
import com.tax_calculator.repositories.schema.CitizensTableSchema;

/**
 ********************************************************************
 * RowMapper object for Citizen objects
 * 
 * This is used by jdbc to convert a cursor row into an object
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CitizenRowMapper implements RowMapper<Citizen> {

	@Override
	public Citizen mapRow(ResultSet rs, int rowNum) throws SQLException {
		Citizen citizen = new Citizen();
		citizen.setId(rs.getLong(CitizensTableSchema.CITIZEN_ID_COL));
		citizen.setName(rs.getString(CitizensTableSchema.NAME_COL));
		citizen.setSalary(rs.getBigDecimal(CitizensTableSchema.SALARY_COL));
		return citizen;
	}

}
