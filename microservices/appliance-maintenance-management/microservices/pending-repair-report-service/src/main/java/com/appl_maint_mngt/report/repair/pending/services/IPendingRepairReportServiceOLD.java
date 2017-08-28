package com.appl_maint_mngt.report.repair.pending.services;
//package com.appl_maint_mngt.report.repair.pending.services;
//
//import java.util.List;
//
//import com.appl_maint_mngt.report.repair.pending.models.PRR;
//
//public interface IPendingRepairReportService {
//	
//	PRR getforId(Long id);
//
//	boolean doesPendingReportExist(Long id);
//	
//	PRR acceptPendingReport(Long id);
//	
//	PRR declinePendingReport(Long id);
//	
//	PRR createPendingRepairReport(PRR report);
//	
//	List<PRR> getPendingForDiagnosticReportIds(Long[] diagRepIds);
//	List<PRR> getPendingForDiagnosticReportId(Long diagRepId);
//	List<PRR> getPendingForOrganisationId(Long orgId);
//	List<PRR> getPendingForEngineerId(Long engId);
//	
//	boolean hasRepairReportBeenAcceptedForDiagRep(Long diagRepId);
//	
//	boolean isPendingReportAcceptedOrPendingForOrgAndDiagRep(Long orgId, Long diagRepId);
//	
//	PRR getForRequest(Long diagRepId, Long orgId);
//
//}