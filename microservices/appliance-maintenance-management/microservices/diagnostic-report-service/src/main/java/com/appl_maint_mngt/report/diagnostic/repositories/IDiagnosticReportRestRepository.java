package com.appl_maint_mngt.report.diagnostic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.report.diagnostic.models.DiagnosticReport;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IDiagnosticReportRestRepository extends JpaRepository<DiagnosticReport, Long> {

	List<DiagnosticReport> findByPropApplId(@Param("propApplId") Long propApplId);
	List<DiagnosticReport> findByPropApplIdIn(@Param("propApplIds") Long[] propApplIds);
	
	List<DiagnosticReport> findByIdIn(@Param("ids") Long[] ids);
}
