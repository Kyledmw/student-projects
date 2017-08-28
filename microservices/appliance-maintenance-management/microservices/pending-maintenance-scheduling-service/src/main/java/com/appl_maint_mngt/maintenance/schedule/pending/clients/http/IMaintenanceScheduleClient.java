package com.appl_maint_mngt.maintenance.schedule.pending.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.appl_maint_mngt.schedule.controllers.rest.IMaintenanceScheduleRestApi;

@FeignClient(value="maintenance-schedule-service", fallback=MaintenanceScheduleClientFallback.class)
public interface IMaintenanceScheduleClient extends IMaintenanceScheduleRestApi {

}
