package com.appl_maint_mngt.diagnostic.request.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appl_maint_mngt.diagnostic.request.models.constants.ResponseStatus;

@Entity
@Table(name="diagnostic_requests")
public class DiagnosticRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="diagnostic_report_id")
	private Long diagnosticReportId;
	
	@Column(name="maintenance_organisation_id")
	private Long maintenanceOrganisationId;
	
	@Column(name="response_status")
	@Enumerated(EnumType.STRING)
	private ResponseStatus responseStatus = ResponseStatus.PENDING;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDiagnosticReportId() {
		return diagnosticReportId;
	}

	public void setDiagnosticReportId(Long diagnosticReportId) {
		this.diagnosticReportId = diagnosticReportId;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Long getMaintenanceOrganisationId() {
		return maintenanceOrganisationId;
	}

	public void setMaintenanceOrganisationId(Long maintenanceOrganisationId) {
		this.maintenanceOrganisationId = maintenanceOrganisationId;
	}
	
}
