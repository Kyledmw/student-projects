package com.appl_maint_mngt.report.repair.pending.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IPendingRepairReportRepository extends JpaRepository<PendingRepairReport, Long> {

	PendingRepairReport findByDiagnosticRequestId(@Param("diagReqId") Long id);
	List<PendingRepairReport> findByDiagnosticRequestIdIn(@Param("diagReqIds") Long[] ids);
	
	List<PendingRepairReport> findByEngineerId(@Param("engId") Long id);
	
	List<PendingRepairReport> findByDiagnosticReportId(Long id);
}
