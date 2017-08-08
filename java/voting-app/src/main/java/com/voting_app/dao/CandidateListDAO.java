package com.voting_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.voting_app.dao.interfaces.ICandidateDAO;
import com.voting_app.models.Candidate;
import com.voting_app.models.PoliticalParty;

/**
 ********************************************************************
 * List {@link List} Implementation of a {@link Candidate} DAO
 * <br>
 * <br>
 * @implements {@link ICandidateDAO}
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CandidateListDAO implements ICandidateDAO {
	
	private int _curHighestCandidateId;
	
	private List<Candidate> _candidateList;
	
	public CandidateListDAO() {
		_candidateList = new CopyOnWriteArrayList<Candidate>();
		_curHighestCandidateId = 0;
	}

	public synchronized Candidate addCandidate(String name, PoliticalParty pparty) {
		_curHighestCandidateId++;
		Candidate candidateToAdd = new Candidate(_curHighestCandidateId, name, pparty);
		_candidateList.add(candidateToAdd);
		return candidateToAdd;
	}

	public synchronized Candidate addCandidate(int id, String name, PoliticalParty pparty) {
		for(Candidate cur: _candidateList) {
			if(cur.getId() == id) {
				return cur;
			}
		}
		if(id > _curHighestCandidateId) {
			_curHighestCandidateId = id;
		}
		Candidate candidateToAdd = new Candidate(id, name, pparty);
		_candidateList.add(candidateToAdd);
		return candidateToAdd;
	}

	public Candidate getCandidate(int id) {
		for(Candidate cur: _candidateList) {
			if(cur.getId() == id) {
				return cur;
			}
		}
		throw new ModelNotFoundException();
	}

	public synchronized void removeCandidate(Candidate candidate) {
		_candidateList.remove(candidate);

	}

	public synchronized void removeCandidate(int id) {
		_candidateList.remove(getCandidate(id));
	}

	public List<Candidate> getCandidates() {
		return _candidateList;
	}

	public synchronized List<Candidate> removeCandidatesInParty(PoliticalParty pparty) {
		List<Candidate> removedList = new ArrayList<Candidate>();
		for(Candidate cur: _candidateList) {
			if(cur.getPoliticalParty().equals(pparty)) {
				removedList.add(cur);
				_candidateList.remove(cur);
			}
		}
		return removedList;
	}

}
