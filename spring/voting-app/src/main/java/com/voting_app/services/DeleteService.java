package com.voting_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;
import com.voting_app.models.PoliticalParty;
import com.voting_app.repositories.interfaces.ICandidateRepository;
import com.voting_app.repositories.interfaces.ICandidateVotesRepository;
import com.voting_app.repositories.interfaces.IConstituencyRepository;
import com.voting_app.repositories.interfaces.IConstituencyVotesRepository;
import com.voting_app.repositories.interfaces.IPoliticalPartyRepository;
import com.voting_app.services.interfaces.IDeleteService;

@Service
public class DeleteService implements IDeleteService {
	
	@Autowired
	private ICandidateRepository _candidateRepo;
	
	@Autowired
	private IPoliticalPartyRepository _ppartyRepo;
	
	@Autowired 
	private IConstituencyRepository _constRepo;
	
	@Autowired
	private ICandidateVotesRepository _candVotesRepo;
	
	@Autowired
	private IConstituencyVotesRepository _constVotesRepo;

	@Override
	public void deleteCandidate(Candidate candidate) {
		_candVotesRepo.deleteVotesForCandidate(candidate);
		_candidateRepo.delete(candidate);
		
	}

	@Override
	public void deletePoliticalParty(PoliticalParty pparty) {
		List<Candidate> candidates = _candidateRepo.getCandidatesForPoliticalParty(pparty);
		_ppartyRepo.delete(pparty);
		for(Candidate c : candidates) {
			deleteCandidate(c);
		}
		
	}

	@Override
	public void deleteConstituency(Constituency constituency) {
		List<Candidate> candidates = _candidateRepo.getCandidatesForConstituency(constituency);
		_constVotesRepo.deleteVotesForConstituency(constituency);
		_constRepo.delete(constituency);
		for(Candidate c : candidates) {
			deleteCandidate(c);
		}
		
	}

}
