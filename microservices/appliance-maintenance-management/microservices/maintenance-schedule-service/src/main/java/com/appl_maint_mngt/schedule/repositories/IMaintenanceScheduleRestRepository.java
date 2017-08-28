package com.appl_maint_mngt.schedule.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.schedule.models.MaintenanceSchedule;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IMaintenanceScheduleRestRepository extends JpaRepository<MaintenanceSchedule, Long> {
	
	MaintenanceSchedule findByRepairReportId(@Param("repairReportId") Long repairReportId);
	List<MaintenanceSchedule> findByRepairReportIdIn(@Param("repairReportIds") Long[] repairReportIds);

}
