package com.voting_app.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.voting_app.dao.interfaces.ICandidateVotesDAO;
import com.voting_app.models.Candidate;
import com.voting_app.models.CandidateVotes;

/**
 ********************************************************************
 * List {@link List} Implementation of a {@link CandidateVotes} DAO
 * <br>
 * <br>
 * @implements {@link ICandidateVotesDAO}
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateVotesListDAO implements ICandidateVotesDAO {
	private List<CandidateVotes> _candidateVotesList;
	
	public CandidateVotesListDAO() {
		_candidateVotesList = new CopyOnWriteArrayList<CandidateVotes>();
		
	}

	public synchronized void addVote(Map<Candidate, Integer> candidateVotes) {
		for(Map.Entry<Candidate, Integer> entry: candidateVotes.entrySet()){
			boolean candidateFound = false;
			for(CandidateVotes cur: _candidateVotesList) {
				if(cur.getCandidate().equals(entry.getKey())) {
					cur.addVote(entry.getValue());
					candidateFound = true;
				}
			}
			if(!candidateFound) {
				CandidateVotes newCandidateVotes = new CandidateVotes(entry.getKey());
				newCandidateVotes.addVote(entry.getValue());
				_candidateVotesList.add(newCandidateVotes);
			}
		}

	}

	public List<CandidateVotes> getCandidateVotes() {
		return _candidateVotesList;
	}

	public synchronized void addCandidateVotes(Candidate candidate, Map<Integer, Integer> votesByRank) {
		_candidateVotesList.add(new CandidateVotes(candidate, votesByRank));

	}

	public synchronized void removeCandidateVotesForCandidate(Candidate candidate) {
		for(CandidateVotes votes :_candidateVotesList) {
			if(votes.getCandidate().equals(candidate)) {
				_candidateVotesList.remove(votes);
				break;
			}
		}
	}

	public void removeCandidateVotesForCandidates(List<Candidate> candidates) {
		for(Candidate candidate: candidates) {
			removeCandidateVotesForCandidate(candidate);
		}
		
	}

}
