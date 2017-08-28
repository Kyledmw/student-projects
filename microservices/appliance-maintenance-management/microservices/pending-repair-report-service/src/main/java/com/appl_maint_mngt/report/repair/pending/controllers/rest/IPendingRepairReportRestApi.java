package com.appl_maint_mngt.report.repair.pending.controllers.rest;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;

public interface IPendingRepairReportRestApi {

	@RequestMapping(value="/{id}/accept", method=RequestMethod.POST)
	@ResponseBody ResponseEntity<PendingRepairReport> acceptReport(@PathVariable("id") Long id);
	
	@RequestMapping(value="/{id}/decline", method=RequestMethod.POST)
	@ResponseBody ResponseEntity<PendingRepairReport> declineReport(@PathVariable("id") Long id);
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody ResponseEntity<PendingRepairReport> create(@Valid @RequestBody PendingRepairReport report);
}
