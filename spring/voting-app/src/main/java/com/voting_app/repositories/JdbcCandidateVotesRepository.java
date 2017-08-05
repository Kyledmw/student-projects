package com.voting_app.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.voting_app.models.Candidate;
import com.voting_app.models.CandidateVote;
import com.voting_app.models.CandidateVotes;
import com.voting_app.repositories.interfaces.ICandidateVotesRepository;
import com.voting_app.repositories.row.mappers.CandidateVotesRowMapper;
import com.voting_app.repositories.row.models.CandidateVotesRow;
import com.voting_app.repositories.schema.CandidateVotesTableSchema;
import com.voting_app.util.sql.ISQLBuilder;

/**
 ********************************************************************
 * JdbcTemplate implementation of ICandidateVotesRepository
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Repository
public class JdbcCandidateVotesRepository implements ICandidateVotesRepository {
	
	@Autowired
	private JdbcTemplate _jdbc;
	
	@Autowired
	private ISQLBuilder _sqlBuilder;

	@Override
	public void addVoteForCandidate(CandidateVote vote) {
		List<String> columns = new ArrayList<String>();
		columns.add(CandidateVotesTableSchema.CANDIDATE_ID_COL);
		columns.add(CandidateVotesTableSchema.VOTE_RANK_COL);
		final String sql = _sqlBuilder.insert(CandidateVotesTableSchema.CANDIDATE_VOTES_TABLE, columns);
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, vote.getCandidateId());
				ps.setInt(2, vote.getRank());
				return ps;
			}
			
		});
	}

	@Override
	public void deleteVotesForCandidate(Candidate candidate) {
		final String sql = _sqlBuilder.deleteStatement(CandidateVotesTableSchema.CANDIDATE_VOTES_TABLE, CandidateVotesTableSchema.CANDIDATE_ID_COL);
		
		_jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, candidate.getId());
				return ps;
			}
			
		});
		
	}

	@Override
	@Transactional(readOnly=true)
	public CandidateVotes getVotesForCandidate(Candidate candidate) {
		final String sql = _sqlBuilder.selectWhereStatement(CandidateVotesTableSchema.CANDIDATE_VOTES_TABLE, CandidateVotesTableSchema.CANDIDATE_ID_COL);
		List<CandidateVotesRow> candidateVotesRows = _jdbc.query(sql, new CandidateVotesRowMapper(), candidate.getId());
		CandidateVotes cVotes = new CandidateVotes();
		cVotes.setCandidate(candidate);
		for(CandidateVotesRow row: candidateVotesRows) {
			cVotes.addVote(row.getVoteRank());
		}
		return cVotes;
	}

}
