package com.appl_maint_mngt.pending_repair_report.models.constants;

import com.google.gson.annotations.SerializedName;

public enum ResponseStatus {
	@SerializedName("CANCELLED")
	CANCELLED,

	@SerializedName("DECLINED")
	DECLINED,

	@SerializedName("PENDING")
	PENDING,

	@SerializedName("ACCEPTED")
	ACCEPTED
}
