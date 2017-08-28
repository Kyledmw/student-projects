package com.appl_maint_mngt.property.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.property.manager.models.PropertyManager;
import com.appl_maint_mngt.property.manager.repositories.IPropertyManagerRestRepository;

@Service
public class PropertyManagerService implements IPropertyManagerService {
	
	@Autowired
	private IPropertyManagerRestRepository propMngRepo;

	@Override
	public void transfer(Long owner, Long receiver, Long propertyid) {
		PropertyManager propMngOwner = propMngRepo.findOne(owner);
		propMngOwner.getCurrentPropertyIds().remove(propertyid);
		PropertyManager propMngRec = propMngRepo.findOne(receiver);
		propMngRec.getCurrentPropertyIds().add(propertyid);
		propMngRepo.save(propMngOwner);
		propMngRepo.save(propMngRec);
	}

	@Override
	public boolean managerExists(Long id) {
		return propMngRepo.findOne(id) != null;
	}

}
