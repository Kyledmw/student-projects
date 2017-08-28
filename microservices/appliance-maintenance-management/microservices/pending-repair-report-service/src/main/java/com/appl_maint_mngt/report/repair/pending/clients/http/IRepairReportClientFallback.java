package com.appl_maint_mngt.report.repair.pending.clients.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.appl_maint_mngt.report.repair.models.IRepairReport;
import com.appl_maint_mngt.report.repair.models.RepairReport;

@Component
public class IRepairReportClientFallback implements IRepairReportClient {

	@Override
	public ResponseEntity<RepairReport> create(IRepairReport report) {
		return new ResponseEntity<RepairReport>(HttpStatus.BAD_REQUEST);
	}

}
