package com.appl_maint_mngt.diagnostic.request.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.diagnostic.request.services.IDiagnosticRequestService;

@RestController
@RequestMapping("/")
public class DiagnosticRequestRestController implements IDiagnosticRequestRestApi {
	
	@Autowired
	private IDiagnosticRequestService service;

	@Override
	public @ResponseBody ResponseEntity<Boolean> deleteForDiagnosticReportId(@PathVariable("id") Long id) {
		service.deleteForDiagnosticReportId(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}

}
