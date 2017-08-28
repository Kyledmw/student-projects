package com.appl_maint_mngt.report.repair.pending.web.constants;

public interface IResponseMessages {

	String PEND_REP_DOESNT_EXIST_ERR = "The given Pending Report Id does not match any records";
	
	String REP_ACCEPTED_ERR = "A repair report has already been accepted for this diagnostic report";

	String ACCEPT_FAILED_ERR = "Failed accepting the pending report.";

	String ACCEPT_SUCCESS = "Accepted the pending repair report successfully.";

	String DECLINE_FAILED_ERR = "Failed to decline the pending repair report";

	String DECLINE_SUCCESS = "Successfully declined the pending repair report";

	String ORG_EXISTS_ERR = "A pending or accepted report already exists for this organisation for the given diagnostic report id";

	String CREATE_SUCCESS = "Created the pending report successfully.";

	String GENERIC_SUCCESS = "SUCCESS";
}
