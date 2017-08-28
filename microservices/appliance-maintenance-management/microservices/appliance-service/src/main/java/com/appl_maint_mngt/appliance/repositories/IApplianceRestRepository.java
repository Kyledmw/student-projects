package com.appl_maint_mngt.appliance.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.appliance.models.Appliance;
import com.appl_maint_mngt.appliance.models.constants.ApplianceType;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IApplianceRestRepository extends MongoRepository<Appliance, String> {
	List<Appliance> findByType(ApplianceType type);
}
