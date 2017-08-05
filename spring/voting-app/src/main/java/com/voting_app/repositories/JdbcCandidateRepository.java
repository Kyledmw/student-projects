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

import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;
import com.voting_app.models.PoliticalParty;
import com.voting_app.repositories.interfaces.ICandidateRepository;
import com.voting_app.repositories.interfaces.IConstituencyRepository;
import com.voting_app.repositories.interfaces.IPoliticalPartyRepository;
import com.voting_app.repositories.row.mappers.CandidateRowMapper;
import com.voting_app.repositories.row.models.CandidateRow;
import com.voting_app.repositories.schema.CandidateTableSchema;
import com.voting_app.util.sql.ISQLBuilder;

/**
 ********************************************************************
 * JdbcTemplate implementation of ICandidateRepository
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Repository
public class JdbcCandidateRepository implements ICandidateRepository {
	
	@Autowired
	private JdbcTemplate _jdbc;
	
	@Autowired
	private ISQLBuilder _sqlBuilder;

	@Autowired
	private IConstituencyRepository _constituencyRepo;

	@Autowired
	private IPoliticalPartyRepository _politicalPartyRepo;
	
	@Override
	@Transactional(readOnly=true)
	public Candidate get(int id) {
		String query = _sqlBuilder.selectWhereStatement(CandidateTableSchema.CANDIDATE_TABLE, CandidateTableSchema.CANDIDATE_ID_COL);
		CandidateRow candidateRow = _jdbc.queryForObject(query, new CandidateRowMapper(), id);
		return createCandidateFromRow(candidateRow);
	}

	@Override
	public void create(Candidate obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(CandidateTableSchema.CANDIDATE_NAME_COL);
		columns.add(CandidateTableSchema.CONSTITUENCY_ID_COL);
		columns.add(CandidateTableSchema.POLITICAL_PARTY_ID_COL);
		final String sql = _sqlBuilder.insert(CandidateTableSchema.CANDIDATE_TABLE, columns);
		KeyHolder holder = new GeneratedKeyHolder();
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, obj.getName());
				ps.setInt(2, obj.getConstituency().getId());
				ps.setInt(3, obj.getPoliticalParty().getId());
				return ps;
			}
			
		}, holder);
		obj.setId(holder.getKey().intValue());
	}

	@Override
	public void delete(Candidate obj) {
		final String sql = _sqlBuilder.deleteStatement(CandidateTableSchema.CANDIDATE_TABLE, CandidateTableSchema.CANDIDATE_ID_COL);
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
	public List<Candidate> getAll() {
		List<CandidateRow> candidateRows = _jdbc.query(_sqlBuilder.selectAllStatement(CandidateTableSchema.CANDIDATE_TABLE), new CandidateRowMapper());
		List<Candidate> candidateList = new ArrayList<Candidate>();
		for(CandidateRow row: candidateRows) {
			candidateList.add(createCandidateFromRow(row));
		}
		return candidateList;
	}
	
	private Candidate createCandidateFromRow(CandidateRow candidateRow) {
		PoliticalParty pparty = _politicalPartyRepo.get(candidateRow.getPoliticalPartyId());
		Constituency constituency = _constituencyRepo.get(candidateRow.getConstituencyId());
		Candidate candidate = new Candidate();
		candidate.setId(candidateRow.getId());
		candidate.setName(candidateRow.getName());
		candidate.setPoliticalParty(pparty);
		candidate.setConstituency(constituency);
		return candidate;
	}

	@Override
	public void update(Candidate obj) {
		List<String> columns = new ArrayList<String>();
		columns.add(CandidateTableSchema.CANDIDATE_NAME_COL);
		columns.add(CandidateTableSchema.CONSTITUENCY_ID_COL);
		columns.add(CandidateTableSchema.POLITICAL_PARTY_ID_COL);
		final String sql = _sqlBuilder.updateStatement(CandidateTableSchema.CANDIDATE_TABLE, columns, CandidateTableSchema.CANDIDATE_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, obj.getName());
				ps.setInt(2, obj.getConstituency().getId());
				ps.setInt(3, obj.getPoliticalParty().getId());
				ps.setInt(4, obj.getId());
				return ps;
			}
			
		});
	}

	@Override
	public List<Candidate> getCandidatesForConstituency(Constituency constituency) {
		final String sql = _sqlBuilder.selectWhereStatement(CandidateTableSchema.CANDIDATE_TABLE, CandidateTableSchema.CONSTITUENCY_ID_COL);
		List<CandidateRow> candidateRows = _jdbc.query(sql, new CandidateRowMapper(), constituency.getId());		
		List<Candidate> candidateList = new ArrayList<Candidate>();
		for(CandidateRow row: candidateRows) {
			candidateList.add(createCandidateFromRow(row));
		}
		return candidateList;
	}

	@Override
	public void deleteCandidatesForConstituency(Constituency constituency) {
		final String sql = _sqlBuilder.deleteStatement(CandidateTableSchema.CANDIDATE_TABLE, CandidateTableSchema.CONSTITUENCY_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, constituency.getId());
				return ps;
			}
			
		});
		
	}

	@Override
	public void deleteCandidatesForPoliticalParty(PoliticalParty pparty) {
		final String sql = _sqlBuilder.deleteStatement(CandidateTableSchema.CANDIDATE_TABLE, CandidateTableSchema.POLITICAL_PARTY_ID_COL);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, pparty.getId());
				return ps;
			}
			
		});
		
	}

	@Override
	public List<Candidate> getCandidatesForPoliticalParty(PoliticalParty pparty) {
		final String sql = _sqlBuilder.selectWhereStatement(CandidateTableSchema.CANDIDATE_TABLE, CandidateTableSchema.POLITICAL_PARTY_ID_COL);
		List<CandidateRow> candidateRows = _jdbc.query(sql, new CandidateRowMapper(), pparty.getId());		
		List<Candidate> candidateList = new ArrayList<Candidate>();
		for(CandidateRow row: candidateRows) {
			candidateList.add(createCandidateFromRow(row));
		}
		return candidateList;
	}

}
