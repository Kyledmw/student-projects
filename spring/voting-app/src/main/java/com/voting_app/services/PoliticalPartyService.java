package com.voting_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting_app.models.PoliticalParty;
import com.voting_app.repositories.interfaces.IPoliticalPartyRepository;
import com.voting_app.services.interfaces.IPoliticalPartyService;

@Service
public class PoliticalPartyService implements IPoliticalPartyService {

	@Autowired
	private IPoliticalPartyRepository _ppartyRepo;
	
	@Override
	public void save(PoliticalParty obj) {
		if(obj.getId() == 0) {
			_ppartyRepo.create(obj);
		} else {
			_ppartyRepo.update(obj);
		}
	}

	@Override
	public PoliticalParty get(int id) {
		return _ppartyRepo.get(id);
	}

	@Override
	public List<PoliticalParty> getAll() {
		return _ppartyRepo.getAll();
	}

}
