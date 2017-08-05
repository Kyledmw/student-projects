package com.voting_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting_app.models.Candidate;
import com.voting_app.models.CandidateVote;
import com.voting_app.models.CandidateVotes;
import com.voting_app.repositories.interfaces.ICandidateVotesRepository;
import com.voting_app.services.interfaces.ICandidateVotesService;

@Service
public class CandidateVotesService implements ICandidateVotesService {
	
	@Autowired
	private ICandidateVotesRepository _candVotesRepo;

	@Override
	public CandidateVotes getVotesForCandidate(Candidate obj) {
		return _candVotesRepo.getVotesForCandidate(obj);
	}

	@Override
	public void vote(CandidateVote vote) {
		_candVotesRepo.addVoteForCandidate(vote);
		
	}

	@Override
	public List<CandidateVotes> getVotesForCandidates(List<Candidate> list) {
		List<CandidateVotes> cvList = new ArrayList<CandidateVotes>();
		for(Candidate c : list) {
			cvList.add(getVotesForCandidate(c));
		}
		return cvList;
	}

	@Override
	public void addVoteList(List<CandidateVote> votes) {
		for(CandidateVote vote: votes) {
			vote(vote);
		}
		
	}

}
