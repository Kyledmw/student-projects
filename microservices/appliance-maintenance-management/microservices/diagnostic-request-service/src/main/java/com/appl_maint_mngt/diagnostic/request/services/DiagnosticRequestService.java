package com.appl_maint_mngt.diagnostic.request.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.diagnostic.request.models.DiagnosticRequest;
import com.appl_maint_mngt.diagnostic.request.repositories.IDiagnosticRequestRestRepository;

@Service
public class DiagnosticRequestService implements IDiagnosticRequestService {
	
	@Autowired
	private IDiagnosticRequestRestRepository repository;

	@Override
	public void deleteForDiagnosticReportId(Long diagnosticReportId) {
		List<DiagnosticRequest> list = new ArrayList<>();
		for(DiagnosticRequest req: repository.findAll()) {
			if(req.getDiagnosticReportId().equals(diagnosticReportId)) {
				list.add(req);
			}
		}
		repository.delete(list);
	}

}
