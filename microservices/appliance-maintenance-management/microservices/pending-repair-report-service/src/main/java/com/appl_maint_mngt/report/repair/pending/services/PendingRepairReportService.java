package com.appl_maint_mngt.report.repair.pending.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.report.repair.models.RepairReport;
import com.appl_maint_mngt.report.repair.pending.clients.http.IRepairReportClient;
import com.appl_maint_mngt.report.repair.pending.clients.http.IDiagnosticRequestsClient;
import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;
import com.appl_maint_mngt.report.repair.pending.models.constants.ResponseStatus;
import com.appl_maint_mngt.report.repair.pending.repositories.IPendingRepairReportRepository;

@Service
public class PendingRepairReportService implements IPendingRepairReportService {

	@Autowired
	private IPendingRepairReportRepository repo;

	@Autowired
	private IDiagnosticRequestsClient diagReqClient;

	@Autowired
	private IRepairReportClient repClient;
	
	@Override
	public boolean doesPendingRepairReportExist(Long id) {
		return repo.findOne(id) != null;
	}
	
	@Override
	public PendingRepairReport accept(Long id) {
		PendingRepairReport pendingRepairReport = repo.findOne(id);
		
		ResponseEntity<RepairReport> response = repClient.create(pendingRepairReport);
		if(response.getStatusCode().is4xxClientError()) return null;
		ResponseEntity<Boolean> diagReqResponse = diagReqClient.deleteForDiagnosticReportId(pendingRepairReport.getDiagnosticReportId());
		if(diagReqResponse.getStatusCode().is4xxClientError()) return null;
		
		List<PendingRepairReport> list = repo.findByDiagnosticReportId(pendingRepairReport.getDiagnosticReportId());
		for(PendingRepairReport item: list) {
			item.setResponseStatus(ResponseStatus.DECLINED);
		}
		repo.save(list);
		pendingRepairReport.setResponseStatus(ResponseStatus.ACCEPTED);
		return repo.save(pendingRepairReport);
	}

	@Override
	public PendingRepairReport decline(Long id) {
		PendingRepairReport pendingRepairReport = repo.findOne(id);
		pendingRepairReport.setResponseStatus(ResponseStatus.DECLINED);
		return repo.save(pendingRepairReport);
	}

	@Override
	public PendingRepairReport create(PendingRepairReport pendingRepairReport) {
		pendingRepairReport.setResponseStatus(ResponseStatus.PENDING);
		return repo.save(pendingRepairReport);
	}
	
	
	@Override
	public boolean hasRepairReportBeenAcceptedForDiagRep(Long diagRepId) {
		List<PendingRepairReport> list = repo.findByDiagnosticReportId(diagRepId);
		for(PendingRepairReport rep: list) {
			if(rep.getResponseStatus().equals(ResponseStatus.ACCEPTED)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public PendingRepairReport get(Long id) {
		return repo.findOne(id);
	}
}
