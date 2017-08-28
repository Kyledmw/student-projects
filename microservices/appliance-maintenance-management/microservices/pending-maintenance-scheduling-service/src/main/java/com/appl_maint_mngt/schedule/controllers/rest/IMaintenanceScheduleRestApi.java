package com.appl_maint_mngt.schedule.controllers.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.schedule.models.web.IMaintenanceSchedulePayload;

public interface IMaintenanceScheduleRestApi {

	@RequestMapping(value="/data", method=RequestMethod.POST)
	@ResponseBody String create(@RequestBody IMaintenanceSchedulePayload payload);
}
