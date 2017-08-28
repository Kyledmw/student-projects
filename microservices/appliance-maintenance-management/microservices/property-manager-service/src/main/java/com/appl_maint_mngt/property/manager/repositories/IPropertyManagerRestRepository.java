package com.appl_maint_mngt.property.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.property.manager.models.PropertyManager;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IPropertyManagerRestRepository extends JpaRepository<PropertyManager, Long>{

}
