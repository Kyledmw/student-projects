//package com.appl_maint_mngt.report.repair.pending.models;
//
//import java.math.BigDecimal;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//import com.appl_maint_mngt.report.repair.models.IRepairReport;
//import com.appl_maint_mngt.report.repair.pending.models.constants.ResponseStatus;
//
//@Entity
//@Table(name="pending_repair_reports")
//public class PRR implements IRepairReport  {
//
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="id")
//	private Long id;
//	
//	@Column(name="organisation_id")
//	private Long organisationId;
//	
//	@Column(name="diagnostic_report_id")
//	@NotNull(message="{rprep.diagrep.null}")
//	private Long diagnosticReportId;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name="response_status")
//	private ResponseStatus responseStatus;
//
//	@Column(name="engineer_id")
//	@NotNull(message="{rprep.engineerid.null}")
//	private Long engineerId;
//	
//	@Column(name="description")
//	@NotNull(message="{rprep.desc.null}")
//	@Size(min=10, max=100, message="{rprep.desc.size}")
//	private String description;
//	
//	@Column(name="estimated_duration_hours")
//	@NotNull(message="{rprep.estdh.null}")
//	@Min(value=1, message="{rprep.estdh.min}")
//	@Max(value=12, message="{rprep.estdh.max}")
//	private int estimatedDurationHours;
//	
//	@Column(name="on_site")
//	@NotNull(message="{rprep.onsite.null}")
//	private boolean onSite;
//	
//	@Column(name="cost")
//	@NotNull(message="{rprep.onsite.cost}")
//	private BigDecimal cost;
//	
//	public Long getId() {
//		return id;
//	}
//	
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	public Long getOrganisationId() {
//		return organisationId;
//	}
//	public void setOrganisationId(Long organisationId) {
//		this.organisationId = organisationId;
//	}
//	public Long getDiagnosticReportId() {
//		return diagnosticReportId;
//	}
//	public void setDiagnosticReportId(Long diagnosticReportId) {
//		this.diagnosticReportId = diagnosticReportId;
//	}
//	public ResponseStatus getResponseStatus() {
//		return responseStatus;
//	}
//	public void setResponseStatus(ResponseStatus responseStatus) {
//		this.responseStatus = responseStatus;
//	}
//
//	@Override
//	public Long getEngineerId() {
//		return engineerId;
//	}
//
//	@Override
//	public void setEngineerId(Long engineerId) {
//		this.engineerId = engineerId;
//		
//	}
//
//	@Override
//	public String getDescription() {
//		return description;
//	}
//
//	@Override
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	@Override
//	public int getEstimatedDurationHours() {
//		return estimatedDurationHours;
//	}
//
//	@Override
//	public void setEstimatedDurationHours(int estimatedDurationHours) {
//		this.estimatedDurationHours = estimatedDurationHours;
//	}
//
//	@Override
//	public boolean isOnSite() {
//		return onSite;
//	}
//
//	@Override
//	public void setOnSite(boolean onSite) {
//		this.onSite = onSite;
//	}
//
//	@Override
//	public BigDecimal getCost() {
//		return cost;
//	}
//
//	@Override
//	public void setCost(BigDecimal cost) {
//		this.cost = cost;
//	}
//}
