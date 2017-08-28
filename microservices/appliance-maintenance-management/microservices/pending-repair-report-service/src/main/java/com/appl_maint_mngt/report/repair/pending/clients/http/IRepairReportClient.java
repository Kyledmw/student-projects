package com.appl_maint_mngt.report.repair.pending.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.appl_maint_mngt.report.repair.controllers.rest.IRepairReportRestApi;

@FeignClient(value="repair-report-service", name="repair-report-service", fallback=IRepairReportClientFallback.class)
public interface IRepairReportClient extends IRepairReportRestApi {

}
