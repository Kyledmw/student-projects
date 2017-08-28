package com.appl_maint_mngt.maintenance.schedule.pending.web.constants;

public interface IResponseMessages {

	String REP_SCHED_ACCEPTED_ERR_MSG = "The given report already has an accepted schedule.";
	
	String ACCEPTED_MSG = "The schedule has been accepted.";
	
	String DECLINE_MSG = "The schedule has been declined";
	
	String INVALID_ID_ERR = "The given id doesn't match any records";
	
	String INVALID_SCHED_TYPE = "The given scheduler type is invalid";
	
	String GENERIC_SUCCESS = "SUCCESS";
	String GENERIC_ERROR = "ERROR";
	
	String CREATE_SUCCESS = "Created pending schedule successfully";
	
	String CREATE_SCHED_ERR = "Error creating maintenance schedule";
}
