package com.tax_calculator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax_calculator.models.Citizen;
import com.tax_calculator.repositories.ICitizensRepository;

/**
 ********************************************************************
 * @Implements ICitizenService
 * 
 * Generic CitizenService that uses an existing ICitizensRepository bean as its DAO
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Service	
public class CitizensService implements ICitizensService {
	
	@Autowired
	private ICitizensRepository _repo;

	@Override
	public void save(Citizen obj) {
		if(obj.getId() == 0) {
			_repo.create(obj);
		} else {
			_repo.update(obj);
		}
		
	}

	@Override
	public List<Citizen> getAll() {
		return _repo.getAll();
	}

}
