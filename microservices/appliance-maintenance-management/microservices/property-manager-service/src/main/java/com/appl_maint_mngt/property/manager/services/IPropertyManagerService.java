package com.appl_maint_mngt.property.manager.services;

public interface IPropertyManagerService {

	void transfer(Long owner, Long receiver, Long propertyid);
	
	boolean managerExists(Long id);
}
