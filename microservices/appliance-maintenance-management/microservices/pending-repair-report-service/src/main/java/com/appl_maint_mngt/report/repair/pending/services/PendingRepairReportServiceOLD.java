package com.appl_maint_mngt.report.repair.pending.services;
//package com.appl_maint_mngt.report.repair.pending.services;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.appl_maint_mngt.report.repair.models.RepairReport;
//import com.appl_maint_mngt.report.repair.pending.clients.http.IRepairReportClient;
//import com.appl_maint_mngt.report.repair.pending.models.PRR;
//import com.appl_maint_mngt.report.repair.pending.models.constants.ResponseStatus;
//import com.appl_maint_mngt.report.repair.pending.repositories.IPendingRepairReportRepository;
//
//@Service
//public class PendingRepairReportService implements IPendingRepairReportService {
//	
//	@Autowired
//	private IPendingRepairReportRepository repo;
//
//	@Autowired
//	private IRepairReportClient repClient;
//
//	@Override
//	public PRR getforId(Long id) {
//		return repo.findOne(id);
//	}
//
//	@Override
//	public boolean doesPendingReportExist(Long id) {
//		return repo.findOne(id) != null;
//	}
//
//	@Override
//	public PRR acceptPendingReport(Long id) {
//		PRR rep = repo.findOne(id);
//		
//		ResponseEntity<RepairReport> response = repClient.create(rep);
//		if(response.getStatusCode().is4xxClientError()) return null; 
//		
//		List<PRR> list = repo.findByDiagnosticReportId(rep.getDiagnosticReportId());
//		for(PRR item : list) {
//			item.setResponseStatus(ResponseStatus.DECLINED);
//		}
//		repo.save(list);
//		rep.setResponseStatus(ResponseStatus.ACCEPTED);
//		return repo.save(rep);
//	}
//
//	@Override
//	public PRR declinePendingReport(Long id) {
//		PRR rep = repo.findOne(id);
//		rep.setResponseStatus(ResponseStatus.DECLINED);
//		return repo.save(rep);
//	}
//
//	@Override
//	public PRR createPendingRepairReport(PRR report) {
//		report.setResponseStatus(ResponseStatus.PENDING);
//		return repo.save(report);
//	}
//
//	@Override
//	public List<PRR> getPendingForDiagnosticReportId(Long diagRepId) {
//		List<PRR> unfilteredList = repo.findByDiagnosticReportId(diagRepId);
//		List<PRR> filteredList = new ArrayList<>();
//		for(PRR rep: unfilteredList) {
//			if(rep.getResponseStatus().equals(ResponseStatus.PENDING)) filteredList.add(rep);
//		}
//		return filteredList;
//	}
//
//	@Override
//	public List<PRR> getPendingForOrganisationId(Long orgId) {
//		List<PRR> unfilteredList = repo.findByOrganisationId(orgId);
//		List<PRR> filteredList = new ArrayList<>();
//		for(PRR rep: unfilteredList) {
//			if(rep.getResponseStatus().equals(ResponseStatus.PENDING)) filteredList.add(rep);
//		}
//		return filteredList;
//		
//	}
//
//	@Override
//	public boolean hasRepairReportBeenAcceptedForDiagRep(Long diagRepId) {
//		List<PRR> list = repo.findByDiagnosticReportId(diagRepId);
//		for(PRR rep: list) {
//			if(rep.getResponseStatus().equals(ResponseStatus.ACCEPTED)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public boolean isPendingReportAcceptedOrPendingForOrgAndDiagRep(Long orgId, Long diagRepId) {
//		List<PRR> list = repo.findByOrganisationId(orgId);
//		for(PRR rep: list) {
//			boolean correctDiagRep = rep.getDiagnosticReportId().equals(diagRepId);
//			boolean invalidStatus = rep.getResponseStatus().equals(ResponseStatus.ACCEPTED)|| rep.getResponseStatus().equals(ResponseStatus.PENDING);
//			if(correctDiagRep && invalidStatus) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//	@Override
//	public PRR getForRequest(Long diagRepId, Long orgId) {
//		List<PRR> unfilteredList = repo.findByOrganisationId(orgId);
//		for(PRR report: unfilteredList) {
//			if(report.getDiagnosticReportId().equals(diagRepId)) return report;
//		}
//		return null;
//	}
//	
//	@Override
//	public List<PRR> getPendingForEngineerId(Long engId) {
//		List<PRR> unfilteredList = repo.findByEngineerId(engId);
//		List<PRR> list = new ArrayList<>();
//		for(PRR rep: unfilteredList) {
//			if(rep.getResponseStatus().equals(ResponseStatus.PENDING)) list.add(rep);
//		}
//		return list;
//	}
//
//	@Override
//	public List<PRR> getPendingForDiagnosticReportIds(Long[] diagRepIds) {
//		return repo.findByDiagnosticReportIdIn(diagRepIds);
//	}
//}
