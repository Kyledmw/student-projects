package com.appl_maint_mngt.report.repair.pending.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;
import com.appl_maint_mngt.report.repair.pending.services.IPendingRepairReportService;

@RestController
@RequestMapping("/")
public class PendingRepairReportRestController implements IPendingRepairReportRestApi {

	@Autowired
	private IPendingRepairReportService pendRepService;
	
	@Override
	public ResponseEntity<PendingRepairReport> acceptReport(@PathVariable("id") Long id) {
		if(!pendRepService.doesPendingRepairReportExist(id)) return new ResponseEntity<PendingRepairReport>(HttpStatus.BAD_REQUEST);
		
		PendingRepairReport pendRepairReport = pendRepService.get(id);
		if(pendRepService.hasRepairReportBeenAcceptedForDiagRep(pendRepairReport.getDiagnosticReportId())) {
			return new ResponseEntity<PendingRepairReport>(HttpStatus.BAD_REQUEST);
		}
		
		pendRepairReport = pendRepService.accept(id);
		if(pendRepairReport == null) {
			return new ResponseEntity<PendingRepairReport>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<PendingRepairReport>(pendRepairReport, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PendingRepairReport> declineReport(@PathVariable("id") Long id) {
		if(!pendRepService.doesPendingRepairReportExist(id)) return new ResponseEntity<PendingRepairReport>(HttpStatus.BAD_REQUEST);
		PendingRepairReport pendRepairReport = pendRepService.decline(id);
		return new ResponseEntity<PendingRepairReport>(pendRepairReport, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<PendingRepairReport> create(PendingRepairReport report) {
		return new ResponseEntity<PendingRepairReport>(pendRepService.create(report), HttpStatus.OK);
	}

//	
//	@Autowired
//	private IDiagnosticRequestsClient diagReqClient;
//	
//	@Autowired
//	private IPendingRepairReportService pendRepService;
//
//	@Override
//	public ApiResponse<PRR> acceptReport(@PathVariable("id") Long id) {
//		if(!pendRepService.doesPendingReportExist(id)) return new ApiResponse<PRR>(ApiResponseStatus.ERROR, IResponseMessages.PEND_REP_DOESNT_EXIST_ERR, null);
//		
//		PRR rep = pendRepService.getforId(id);
//		
//		if(pendRepService.hasRepairReportBeenAcceptedForDiagRep(rep.getDiagnosticReportId())) return new ApiResponse<PRR>(ApiResponseStatus.ERROR, IResponseMessages.REP_ACCEPTED_ERR, null);
//		
//		PRR report = pendRepService.acceptPendingReport(id);
//		
//		if(report == null) return new ApiResponse<PRR>(ApiResponseStatus.ERROR, IResponseMessages.ACCEPT_FAILED_ERR, null);
//
//		ResponseEntity<Boolean> diagReqResp = diagReqClient.deleteForDiagnosticReportId(rep.getDiagnosticReportId());
//		if(!diagReqResp.getBody()) return new ApiResponse<PRR>(ApiResponseStatus.ERROR, IResponseMessages.ACCEPT_FAILED_ERR, null);
//			
//		return new ApiResponse<PRR>(ApiResponseStatus.SUCCESS, IResponseMessages.ACCEPT_SUCCESS, report);
//	}
//
//	@Override
//	public ApiResponse<PRR> declineReport(@PathVariable("id") Long id) {
//		if(!pendRepService.doesPendingReportExist(id)) return new ApiResponse<PRR>(ApiResponseStatus.ERROR, IResponseMessages.PEND_REP_DOESNT_EXIST_ERR, null);
//		
//		PRR rep = pendRepService.getforId(id);
//		
//		if(pendRepService.hasRepairReportBeenAcceptedForDiagRep(rep.getDiagnosticReportId())) return new ApiResponse<PRR>(ApiResponseStatus.ERROR, IResponseMessages.REP_ACCEPTED_ERR, null);
//		
//		PRR report = pendRepService.declinePendingReport(id);
//		
//		if(report == null) return new ApiResponse<PRR>(ApiResponseStatus.ERROR, IResponseMessages.DECLINE_FAILED_ERR, null);
//
//		return new ApiResponse<PRR>(ApiResponseStatus.SUCCESS, IResponseMessages.DECLINE_SUCCESS, report);
//	}
//
//	@Override
//	public ApiResponse<PRR> create(@Valid @RequestBody PRR report) {
//		boolean orgInvalid = pendRepService.isPendingReportAcceptedOrPendingForOrgAndDiagRep(report.getOrganisationId(), report.getDiagnosticReportId());
//		
//		if(orgInvalid) return new ApiResponse<>(ApiResponseStatus.ERROR, IResponseMessages.ORG_EXISTS_ERR, null);
//		
//		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.CREATE_SUCCESS, pendRepService.createPendingRepairReport(report));
//	}

}
