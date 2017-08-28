package com.appl_maint_mngt.maintenance.engineer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.maintenance.engineer.models.MaintenanceEngineer;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IMaintenanceEngineerRestRepository extends JpaRepository<MaintenanceEngineer, Long>{

}
