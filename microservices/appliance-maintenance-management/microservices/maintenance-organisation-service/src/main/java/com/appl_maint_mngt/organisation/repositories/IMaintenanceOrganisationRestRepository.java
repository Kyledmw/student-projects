package com.appl_maint_mngt.organisation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.organisation.models.MaintenanceOrganisation;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IMaintenanceOrganisationRestRepository extends JpaRepository<MaintenanceOrganisation, Long> {

}
