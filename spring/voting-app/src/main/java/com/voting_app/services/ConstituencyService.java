package com.voting_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting_app.models.Constituency;
import com.voting_app.repositories.interfaces.IConstituencyRepository;
import com.voting_app.services.interfaces.IConstituencyService;

@Service
public class ConstituencyService implements IConstituencyService {
	
	@Autowired
	private IConstituencyRepository _constRepo;

	@Override
	public void save(Constituency obj) {
		if(obj.getId() == 0){
			_constRepo.create(obj);
		} else {
			_constRepo.update(obj);
		}
		
	}
	@Override
	public Constituency get(int id) {
		return _constRepo.get(id);
	}

	@Override
	public List<Constituency> getAll() {
		return _constRepo.getAll();
	}

}
