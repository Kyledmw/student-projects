 package com.appl_maint_mngt.pending_maintenance_scheduling.models.constants;

import com.google.gson.annotations.SerializedName;

public enum ScheduleStatus {

	@SerializedName("ACCEPTED")
	ACCEPTED,

	@SerializedName("DECLINED")
	DECLINED,

	@SerializedName("PENDING")
	PENDING,

	@SerializedName("CANCELLED")
	CANCELLED
}
