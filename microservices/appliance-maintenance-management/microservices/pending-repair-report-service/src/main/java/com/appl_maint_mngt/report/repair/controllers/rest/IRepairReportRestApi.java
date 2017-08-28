package com.appl_maint_mngt.report.repair.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.report.repair.models.IRepairReport;
import com.appl_maint_mngt.report.repair.models.RepairReport;

public interface IRepairReportRestApi {

	@RequestMapping(value="/data", method=RequestMethod.POST)
	@ResponseBody ResponseEntity<RepairReport> create(@RequestBody IRepairReport report);
}
