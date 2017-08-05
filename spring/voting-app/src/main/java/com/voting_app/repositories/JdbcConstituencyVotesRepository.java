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
import com.voting_app.models.ConstituencyVotes;
import com.voting_app.repositories.interfaces.IConstituencyRepository;
import com.voting_app.repositories.interfaces.IConstituencyVotesRepository;
import com.voting_app.repositories.row.mappers.ConstituencyVotesRowMapper;
import com.voting_app.repositories.row.models.ConstituencyVotesRow;
import com.voting_app.repositories.schema.ConstituencyVotesTableSchema;
import com.voting_app.util.sql.ISQLBuilder;

/**
 ********************************************************************
 * JdbcTemplate implementation of IConstituencyVotesRepository
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Repository
public class JdbcConstituencyVotesRepository implements IConstituencyVotesRepository {

	@Autowired
	private JdbcTemplate _jdbc;
	
	@Autowired
	private ISQLBuilder _sqlBuilder;
	
	@Autowired
	private IConstituencyRepository _constituencyRepo;
	
	@Override
	@Transactional(readOnly=true)
	public ConstituencyVotes get(int id) {
		String sql = _sqlBuilder.selectWhereStatement(ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_TABLE, ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_ID_COL);
		ConstituencyVotesRow constituencyVotesRow = _jdbc.queryForObject(sql, new ConstituencyVotesRowMapper(), id);
		ConstituencyVotes cVotes = new ConstituencyVotes();
		cVotes.setId(constituencyVotesRow.getId());
		cVotes.setValidVotes(constituencyVotesRow.getValidVotes());
		cVotes.setInvalidVotes(constituencyVotesRow.getInvalidVotes());
		cVotes.setConstituency(_constituencyRepo.get(constituencyVotesRow.getConstituencyId()));
		return cVotes ;
	}

	@Override
	public void create(ConstituencyVotes obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(ConstituencyVotesTableSchema.CONSTITUENCY_ID_COL);
		columns.add(ConstituencyVotesTableSchema.VALID_VOTES_COL);
		columns.add(ConstituencyVotesTableSchema.INVALID_VOTES_COL);
		final String sql = _sqlBuilder.insert(ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_TABLE, columns);
		KeyHolder holder = new GeneratedKeyHolder();
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, obj.getConstituency().getId());
				ps.setInt(2, obj.getValidVotes());
				ps.setInt(3, obj.getInvalidVotes());
				return ps;
			}
			
		}, holder);
		obj.setId(holder.getKey().intValue());
	}

	@Override
	public void update(ConstituencyVotes obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(ConstituencyVotesTableSchema.CONSTITUENCY_ID_COL);
		columns.add(ConstituencyVotesTableSchema.VALID_VOTES_COL);
		columns.add(ConstituencyVotesTableSchema.INVALID_VOTES_COL);
		
		final String sql = _sqlBuilder.updateStatement(ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_TABLE, columns, ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, obj.getConstituency().getId());
				ps.setInt(2, obj.getValidVotes());
				ps.setInt(3, obj.getInvalidVotes());
				ps.setInt(4, obj.getId());
				return ps;
			}
			
		});
		
	}

	@Override
	public void delete(ConstituencyVotes obj) {
		final String sql = _sqlBuilder.deleteStatement(ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_TABLE, ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_ID_COL);
		
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
	public List<ConstituencyVotes> getAll() {
		List<ConstituencyVotesRow> constituencyVotesRows = _jdbc.query(_sqlBuilder.selectAllStatement(ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_TABLE), new ConstituencyVotesRowMapper());
		List<ConstituencyVotes> constituencyVotesList = new ArrayList<ConstituencyVotes>();
		for(ConstituencyVotesRow row: constituencyVotesRows) {
			constituencyVotesList.add(getConstituencyVotesFromRow(row));
		}
		return constituencyVotesList;
	}

	@Override
	@Transactional(readOnly=true)
	public ConstituencyVotes getVotesForConstituency(Constituency constituency) {
		final String sql = _sqlBuilder.selectWhereStatement(ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_TABLE, ConstituencyVotesTableSchema.CONSTITUENCY_ID_COL);
		ConstituencyVotesRow constituencyVotesRow = _jdbc.queryForObject(sql, new ConstituencyVotesRowMapper(), constituency.getId());
		return getConstituencyVotesFromRow(constituencyVotesRow);
	}
	
	private ConstituencyVotes getConstituencyVotesFromRow(ConstituencyVotesRow row) {
		ConstituencyVotes cVotes = new ConstituencyVotes();
		cVotes.setId(row.getId());
		cVotes.setConstituency(_constituencyRepo.get(row.getConstituencyId()));
		cVotes.setValidVotes(row.getValidVotes());
		cVotes.setInvalidVotes(row.getInvalidVotes());
		return cVotes;
	}

	@Override
	public void deleteVotesForConstituency(Constituency constituency) {
		final String sql = _sqlBuilder.deleteStatement(ConstituencyVotesTableSchema.CONSTITUENCY_VOTES_TABLE, ConstituencyVotesTableSchema.CONSTITUENCY_ID_COL);
		
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1,  constituency.getId());
				return ps;
			}
			
		});
	}

}
