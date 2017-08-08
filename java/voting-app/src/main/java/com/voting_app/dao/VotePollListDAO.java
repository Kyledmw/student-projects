package com.voting_app.dao;

import java.util.List;

import com.voting_app.dao.interfaces.ICandidateDAO;
import com.voting_app.dao.interfaces.ICandidateVotesDAO;
import com.voting_app.dao.interfaces.IPoliticalPartyDAO;
import com.voting_app.dao.interfaces.IVotePollDAO;
import com.voting_app.models.Candidate;
import com.voting_app.models.PoliticalParty;
import com.voting_app.models.VotingPoll;

/**
 ********************************************************************
 * List {@link List} Implementation of a {@link VotingPoll} DAO
 * It also provides {@link List} implementations for other relevant DAO
 * <br>
 * <br>
 * @implements {@link IVotePollDAO}
 * 
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class VotePollListDAO implements IVotePollDAO {
	
	private VotingPoll _votingPoll;
	
	private IPoliticalPartyDAO _politicalPartyDAO;	
	private ICandidateDAO _candidateDAO;
	private ICandidateVotesDAO _candidateVotesDAO;
	
	public VotePollListDAO() {
		_votingPoll = new VotingPoll();
		_politicalPartyDAO = new PoliticalPartyListDAO();
		_candidateDAO = new CandidateListDAO();
		_candidateVotesDAO = new CandidateVotesListDAO();
	}


	public synchronized void incrementInvalidVotes() {
		_votingPoll.incrementInvalidVotes();
	}



	public VotingPoll getVotingPoll() {
		return _votingPoll;
	}

	public synchronized void initVotingPoll(int validVotes, int invalidVotes) {
		_votingPoll.setValidVotes(validVotes);
		_votingPoll.setInvalidVotes(invalidVotes);
		
	}


	public IPoliticalPartyDAO getPoliticalPartyDAO() {
		return _politicalPartyDAO;
	}


	public ICandidateVotesDAO getCandidateVotesDAO() {
		return _candidateVotesDAO;
	}


	public ICandidateDAO getCandidateDAO() {
		return _candidateDAO;
	}
	
	public void removePoliticalParty(PoliticalParty pparty) {
		_politicalPartyDAO.removePoliticalParty(pparty.getId());
		List<Candidate> removedList = _candidateDAO.removeCandidatesInParty(pparty);
		_candidateVotesDAO.removeCandidateVotesForCandidates(removedList);
	}
	
	public void removeCandidate(Candidate candidate) {
		_candidateDAO.removeCandidate(candidate);
		_candidateVotesDAO.removeCandidateVotesForCandidate(candidate);
	}


	public void incrementVotes() {
		_votingPoll.incrementValidVotes();
	}
}
