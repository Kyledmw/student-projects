package com.tax_calculator.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tax_calculator.models.Citizen;
import com.tax_calculator.repositories.row_mappers.CitizenRowMapper;
import com.tax_calculator.repositories.schema.CitizensTableSchema;
import com.tax_calculator.util.sql.ISQLBuilder;

/**
 ********************************************************************
 * JdbcTemplate implementation of ICitizensRepository
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Repository
public class JDBCCitizensRepository implements ICitizensRepository {
	
	@Autowired
	private JdbcTemplate _jdbc;
	
	@Autowired
	private ISQLBuilder _sqlBuilder;

	@Override
	@Transactional(readOnly=true)
	public Citizen get(long id) {
		String query = _sqlBuilder.selectWhereStatement(CitizensTableSchema.CITIZENS_TABLE_SCHEMA, CitizensTableSchema.CITIZEN_ID_COL);
		Citizen citizen = _jdbc.queryForObject(query, new CitizenRowMapper(), id);
		return citizen;
	}

	@Override
	public void create(Citizen obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(CitizensTableSchema.NAME_COL);
		columns.add(CitizensTableSchema.SALARY_COL);
		final String sql = _sqlBuilder.insert(CitizensTableSchema.CITIZENS_TABLE_SCHEMA, columns);
		KeyHolder holder = new GeneratedKeyHolder();
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, obj.getName());
				ps.setBigDecimal(2, obj.getSalary());
				return ps;
			}
			
		}, holder);
		obj.setId(holder.getKey().longValue());
	}

	@Override
	public void update(Citizen obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(CitizensTableSchema.NAME_COL);
		columns.add(CitizensTableSchema.SALARY_COL);
		final String sql = _sqlBuilder.updateStatement(CitizensTableSchema.CITIZENS_TABLE_SCHEMA, columns, CitizensTableSchema.CITIZEN_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, obj.getName());
				ps.setBigDecimal(2, obj.getSalary());
				ps.setLong(3, obj.getId());
				return ps;
			}
		});
	}

	@Override
	public void delete(Citizen obj) {
		final String sql = _sqlBuilder.deleteStatement(CitizensTableSchema.CITIZENS_TABLE_SCHEMA, CitizensTableSchema.CITIZEN_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setLong(1,  obj.getId());
				return ps;
			}
			
		});
	}

	@Override
	@Transactional(readOnly=true)
	public List<Citizen> getAll() {
		String sql = _sqlBuilder.selectAllStatement(CitizensTableSchema.CITIZENS_TABLE_SCHEMA);
		List<Citizen> citizens = _jdbc.query(sql, new CitizenRowMapper());
		return citizens;
	}

}
