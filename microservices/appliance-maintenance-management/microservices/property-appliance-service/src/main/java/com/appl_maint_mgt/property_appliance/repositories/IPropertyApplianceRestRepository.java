package com.appl_maint_mgt.property_appliance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mgt.property_appliance.models.PropertyAppliance;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IPropertyApplianceRestRepository extends JpaRepository<PropertyAppliance, Long> {

	List<PropertyAppliance> findByPropertyId(@Param("propertyId") Long propertyId);
	List<PropertyAppliance> findByPropertyIdIn(@Param("propertyIds") Long[] propertyIds);
}
