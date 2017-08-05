package com.tax_calculator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax_calculator.models.Tax;
import com.tax_calculator.repositories.ITaxationRepository;

/**
 ********************************************************************
 * @Implements ITaxationService
 * 
 * Generic TaxationService that uses an existing ITaxationRepository bean as its DAO
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Service
public class TaxationService implements ITaxationService {
	
	@Autowired
	private ITaxationRepository _repo;
	
	@Override
	public void save(Tax obj) {
		if(obj.getId() == 0) {
			_repo.create(obj);
		} else {
			_repo.update(obj);
		}
	}

	@Override
	public void delete(Tax obj) {
		_repo.delete(obj);
	}

	@Override
	public List<Tax> getAll() {
		return _repo.getAll();
	}

}
