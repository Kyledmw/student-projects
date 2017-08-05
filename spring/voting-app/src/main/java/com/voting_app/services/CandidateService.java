package com.voting_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;
import com.voting_app.repositories.interfaces.ICandidateRepository;
import com.voting_app.services.interfaces.ICandidateService;

@Service
public class CandidateService implements ICandidateService {
	
	@Autowired
	private ICandidateRepository _candRepo;

	@Override
	public void save(Candidate obj) {
		if(obj.getId() == 0) {
			_candRepo.create(obj);
		} else {
			_candRepo.update(obj);
		}
	}

	@Override
	public Candidate get(int id) {
		return _candRepo.get(id);
	}

	@Override
	public List<Candidate> getAll() {
		return _candRepo.getAll();
	}

	@Override
	public List<Candidate> getCandidatesForConstituency(Constituency constituency) {
		return _candRepo.getCandidatesForConstituency(constituency);
	}

}
