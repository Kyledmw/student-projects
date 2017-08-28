package com.appl_maint_mngt.maintenance.schedule.pending.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.models.web.PendingSchedulePayload;

public interface IPendingMaintenanceSchedulingRestApi {
	
	@RequestMapping(value="/{id}/accept", method=RequestMethod.POST)
	@ResponseBody ApiResponse<PendingMaintenanceSchedule> accept(@PathVariable("id") Long id);
	
	@RequestMapping(value="/{id}/decline", method=RequestMethod.POST)
	@ResponseBody ApiResponse<PendingMaintenanceSchedule> decline(@PathVariable("id") Long id);
	
	@RequestMapping(value="/report/{id}/pending", method=RequestMethod.GET)
	@ResponseBody ApiResponse<List<PendingMaintenanceSchedule>> getPendingSchedules(@PathVariable("id") Long id, @RequestParam("schedulerType") String type);
	
	@RequestMapping(value="/report/{id}/", method=RequestMethod.GET)
	@ResponseBody ResponseEntity<List<PendingMaintenanceSchedule>> getPendingSchedules(@PathVariable("id") Long id);

	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody ApiResponse<PendingMaintenanceSchedule> add(@Valid @RequestBody PendingSchedulePayload payload);
}
