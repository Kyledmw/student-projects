package com.appl_maint_mngt.diagnostic.request.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appl_maint_mngt.diagnostic.request.models.DiagnosticRequest;

@RepositoryRestResource(collectionResourceRel="data", path="data")
public interface IDiagnosticRequestRestRepository extends JpaRepository<DiagnosticRequest, Long> {

	List<DiagnosticRequest> findByDiagnosticReportId(@Param("diagRepId") Long id);
	List<DiagnosticRequest> findByDiagnosticReportIdIn(@Param("diagRepIds") Long[] ids);
	List<DiagnosticRequest> findByMaintenanceOrganisationId(@Param("maintOrgId") Long id);
}
