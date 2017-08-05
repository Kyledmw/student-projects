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

import com.voting_app.models.PoliticalParty;
import com.voting_app.repositories.interfaces.IPoliticalPartyRepository;
import com.voting_app.repositories.row.mappers.PoliticalPartyRowMapper;
import com.voting_app.repositories.schema.PoliticalPartyTableSchema;
import com.voting_app.util.sql.ISQLBuilder;

/**
 ********************************************************************
 * JdbcTemplate implementation of IPoliticalPartyRepository
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Repository
public class JdbcPoliticalPartyRepository implements IPoliticalPartyRepository {
	
	@Autowired
	private JdbcTemplate _jdbc;
	
	@Autowired
	private ISQLBuilder _sqlBuilder;

	@Override
	@Transactional(readOnly=true)
	public PoliticalParty get(int id) {
		return _jdbc.queryForObject(_sqlBuilder.selectWhereStatement(PoliticalPartyTableSchema.POLITICAL_PARTY_TABLE, PoliticalPartyTableSchema.POLITICAL_PARTY_ID_COL), new PoliticalPartyRowMapper(), id);
	}

	@Override
	public void create(PoliticalParty obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(PoliticalPartyTableSchema.POLITICAL_PARTY_NAME_COL);
		final String sql = _sqlBuilder.insert(PoliticalPartyTableSchema.POLITICAL_PARTY_TABLE, columns);
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
	public void delete(PoliticalParty obj) {
		final String sql = _sqlBuilder.deleteStatement(PoliticalPartyTableSchema.POLITICAL_PARTY_TABLE, PoliticalPartyTableSchema.POLITICAL_PARTY_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, obj.getId());
				return ps;
			}
			
		});
	}

	@Override
	@Transactional(readOnly=true)
	public List<PoliticalParty> getAll() {
		return _jdbc.query(_sqlBuilder.selectAllStatement(PoliticalPartyTableSchema.POLITICAL_PARTY_TABLE), new PoliticalPartyRowMapper());
	}

	@Override
	public void update(PoliticalParty obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(PoliticalPartyTableSchema.POLITICAL_PARTY_NAME_COL);
		final String sql = _sqlBuilder.updateStatement(PoliticalPartyTableSchema.POLITICAL_PARTY_TABLE, columns, PoliticalPartyTableSchema.POLITICAL_PARTY_ID_COL);
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
