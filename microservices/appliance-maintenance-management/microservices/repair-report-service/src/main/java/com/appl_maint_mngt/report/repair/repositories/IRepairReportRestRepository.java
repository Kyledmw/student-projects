package com.appl_maint_mngt.report.repair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.report.repair.models.RepairReport;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IRepairReportRestRepository extends JpaRepository<RepairReport, Long> {

	RepairReport findByDiagnosticReportId(@Param("diagRepId") Long diagRepId);
	List<RepairReport> findByDiagnosticReportIdIn(@Param("diagRepIds") Long[] diagRepId);
	
	List<RepairReport> findByEngineerId(@Param("engineerId") Long engineerId);
}
