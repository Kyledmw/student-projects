package com.voting_app.repositories;

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

import com.voting_app.models.Constituency;
import com.voting_app.repositories.interfaces.IConstituencyRepository;
import com.voting_app.repositories.row.mappers.ConstituencyRowMapper;
import com.voting_app.repositories.schema.ConstituencyTableSchema;
import com.voting_app.util.sql.ISQLBuilder;

/**
 ********************************************************************
 * JdbcTemplate implementation of IConstituencyRepository
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Repository
public class JdbcConstituencyRepository implements IConstituencyRepository {
	
	@Autowired
	private JdbcTemplate _jdbc;

	@Autowired
	private ISQLBuilder _sqlBuilder;

	@Override
	@Transactional(readOnly=true)
	public Constituency get(int id) {
		return _jdbc.queryForObject(_sqlBuilder.selectWhereStatement(ConstituencyTableSchema.CONSTITUENCY_TABLE, ConstituencyTableSchema.CONSTITUENCY_ID_COL), new ConstituencyRowMapper(), id);
	}

	@Override
	public void create(Constituency obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(ConstituencyTableSchema.CONSTITUENCY_NAME_COL);
		final String sql = _sqlBuilder.insert(ConstituencyTableSchema.CONSTITUENCY_TABLE, columns);
		KeyHolder holder = new GeneratedKeyHolder();
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, obj.getName());
				return ps;
			}
			
		}, holder);
		obj.setId(holder.getKey().intValue());
	}

	@Override
	public void delete(Constituency obj) {
		final String sql = _sqlBuilder.deleteStatement(ConstituencyTableSchema.CONSTITUENCY_TABLE, ConstituencyTableSchema.CONSTITUENCY_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1,  obj.getId());
				return ps;
			}
			
		});
	}

	@Override
	@Transactional(readOnly=true)
	public List<Constituency> getAll() {
		return _jdbc.query(_sqlBuilder.selectAllStatement(ConstituencyTableSchema.CONSTITUENCY_TABLE), new ConstituencyRowMapper());
	}

	@Override
	public void update(Constituency obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(ConstituencyTableSchema.CONSTITUENCY_NAME_COL);
		final String sql = _sqlBuilder.updateStatement(ConstituencyTableSchema.CONSTITUENCY_TABLE, columns, ConstituencyTableSchema.CONSTITUENCY_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, obj.getName());
				ps.setInt(2, obj.getId());
				return ps;
			}
			
		});
	}

}
