package com.appl_maint_mngt.report.repair.pending.clients.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticRequestClientFallback implements IDiagnosticRequestsClient {

	@Override
	public ResponseEntity<Boolean> deleteForDiagnosticReportId(Long id) {
		return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}

}
