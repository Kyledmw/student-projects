package com.appl_maint_mngt.maintenance.schedule.pending.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.ApiResponseStatus;
import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.SchedulerType;
import com.appl_maint_mngt.maintenance.schedule.pending.models.converters.IPendingSchedulePayloadConverter;
import com.appl_maint_mngt.maintenance.schedule.pending.models.web.PendingSchedulePayload;
import com.appl_maint_mngt.maintenance.schedule.pending.services.ICreateMaintenanceScheduleService;
import com.appl_maint_mngt.maintenance.schedule.pending.services.IPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.maintenance.schedule.pending.web.constants.IResponseMessages;

@RestController
@RequestMapping("/")	
public class PendingMaintenanceSchedulingRestController implements IPendingMaintenanceSchedulingRestApi {
	
	@Autowired
	private IPendingMaintenanceSchedulingService pendMainSchedServ;
	
	@Autowired
	private ICreateMaintenanceScheduleService maintSchedService;
	
	@Autowired
	private IPendingSchedulePayloadConverter converter;

	@Override
	public ApiResponse<PendingMaintenanceSchedule> accept(@PathVariable("id") Long id) {
		if(!pendMainSchedServ.doesItemExist(id)) {
			return new ApiResponse<PendingMaintenanceSchedule>(ApiResponseStatus.ERROR, IResponseMessages.INVALID_ID_ERR, null);
		}
		Long reportId = pendMainSchedServ.getRepairReportForId(id);
		if(pendMainSchedServ.doesReportHaveAcceptedSchedule(reportId)) {
			return new ApiResponse<PendingMaintenanceSchedule>(ApiResponseStatus.ERROR, IResponseMessages.REP_SCHED_ACCEPTED_ERR_MSG, null);
		}
		boolean res = maintSchedService.attemptScheduleCreation(pendMainSchedServ.getForId(id));
		if(!res) {
			return new ApiResponse<PendingMaintenanceSchedule>(ApiResponseStatus.ERROR, IResponseMessages.CREATE_SCHED_ERR, null);
		}
		return new ApiResponse<PendingMaintenanceSchedule>(ApiResponseStatus.SUCCESS, IResponseMessages.ACCEPTED_MSG, pendMainSchedServ.acceptPendingSchedule(id));
	}

	@Override
	public ApiResponse<PendingMaintenanceSchedule> decline(@PathVariable("id") Long id) {
		if(!pendMainSchedServ.doesItemExist(id)) {
			return new ApiResponse<PendingMaintenanceSchedule>(ApiResponseStatus.ERROR, IResponseMessages.INVALID_ID_ERR, null);
		}
		Long reportId = pendMainSchedServ.getRepairReportForId(id);
		if(pendMainSchedServ.doesReportHaveAcceptedSchedule(reportId)) {
			return new ApiResponse<PendingMaintenanceSchedule>(ApiResponseStatus.ERROR, IResponseMessages.REP_SCHED_ACCEPTED_ERR_MSG, null);
		}
		return new ApiResponse<PendingMaintenanceSchedule>(ApiResponseStatus.SUCCESS, IResponseMessages.DECLINE_MSG, pendMainSchedServ.declinePendingSchedule(id));
	}

	@Override
	public ApiResponse<List<PendingMaintenanceSchedule>> getPendingSchedules(@PathVariable("id") Long id, @RequestParam("schedulerType") String type) {
		SchedulerType schedType;
		try {
			schedType = SchedulerType.valueOf(type);
		} catch(Exception e) { 
			return new ApiResponse<>(ApiResponseStatus.ERROR, IResponseMessages.INVALID_SCHED_TYPE, new ArrayList<>());
		}
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.GENERIC_SUCCESS, pendMainSchedServ.getAllForReportAndSchedulerType(id, schedType));
	}

	@Override
	public ApiResponse<PendingMaintenanceSchedule> add(@Valid @RequestBody PendingSchedulePayload payload) {
		Long reportId = pendMainSchedServ.getRepairReportForId(payload.getReportId());
		if(pendMainSchedServ.doesReportHaveAcceptedSchedule(reportId)) {
			return new ApiResponse<PendingMaintenanceSchedule>(ApiResponseStatus.ERROR, IResponseMessages.REP_SCHED_ACCEPTED_ERR_MSG, null);
		}
		pendMainSchedServ.save(converter.toPendingMaintSched(payload));
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, IResponseMessages.CREATE_SUCCESS, pendMainSchedServ.save(converter.toPendingMaintSched(payload)));
	}

	@Override
	public ResponseEntity<List<PendingMaintenanceSchedule>> getPendingSchedules(@PathVariable("id") Long id) {
		pendMainSchedServ.getAllForReport(id);
		return new ResponseEntity<List<PendingMaintenanceSchedule>>(pendMainSchedServ.getAllForReport(id), HttpStatus.OK);
	}

}
