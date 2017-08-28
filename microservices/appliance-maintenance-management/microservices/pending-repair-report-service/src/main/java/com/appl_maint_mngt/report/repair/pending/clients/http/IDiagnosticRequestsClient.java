package com.appl_maint_mngt.report.repair.pending.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.appl_maint_mngt.diagnostic.request.controllers.rest.IDiagnosticRequestRestApi;

@FeignClient(value="diagnostic-request-service", name="diagnostic-request-service")
public interface IDiagnosticRequestsClient extends IDiagnosticRequestRestApi {

}
