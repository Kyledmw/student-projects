package com.appl_maint_mngt.property.tenant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.property.tenant.models.PropertyTenant;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IPropertyTenantRestRepository extends JpaRepository<PropertyTenant, Long> {
	
	List<PropertyTenant> findByCurrentPropertyId(@Param("propertyId") Long propertyId);

}
