package com.appl_maint_mngt.appliance.status.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance.status.models.ApplianceStatus;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IApplianceStatusRestRepository extends JpaRepository<ApplianceStatus, Long> {
	List<ApplianceStatus> findByIdIn(@Param("ids") Long[] ids);
	List<ApplianceStatus> findByApplianceTypeIn(@Param("types") ApplianceType[] types);
}
