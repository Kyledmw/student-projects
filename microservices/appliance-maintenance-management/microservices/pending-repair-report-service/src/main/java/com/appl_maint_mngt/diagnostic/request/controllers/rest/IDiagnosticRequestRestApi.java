package com.appl_maint_mngt.diagnostic.request.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IDiagnosticRequestRestApi {

	@RequestMapping(value="/report/{id}/delete", method=RequestMethod.DELETE)
	@ResponseBody ResponseEntity<Boolean> deleteForDiagnosticReportId(@PathVariable("id") Long id);
}
