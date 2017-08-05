package com.voting_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting_app.models.Constituency;
import com.voting_app.models.ConstituencyVotes;
import com.voting_app.repositories.interfaces.IConstituencyVotesRepository;
import com.voting_app.services.interfaces.IConstituencyVotesService;

@Service
public class ConstituencyVotesService implements IConstituencyVotesService{
	
	@Autowired
	private IConstituencyVotesRepository _constVotesRepo;
	
	@Override
	public ConstituencyVotes getVotesForConstiuency(Constituency constituency) {
		return _constVotesRepo.getVotesForConstituency(constituency);
	}

	@Override
	public void save(ConstituencyVotes votes) {
		if(votes.getId() == 0) {
			_constVotesRepo.create(votes);
		} else {
			_constVotesRepo.update(votes);
		}
		
	}

}
