package com.appl_maint_mngt.property.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.property.models.Property;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IPropertyRestRepository extends JpaRepository<Property, Long> {
	List<Property> findByIdIn(@Param("ids") Long[] ids);
}
