package com.appl_maint_mngt.diagnostic_request.models.constants;

import com.google.gson.annotations.SerializedName;

public enum ResponseStatus {
	@SerializedName("PENDING")
	PENDING,

	@SerializedName("RESPONDED")
	RESPONDED,

	@SerializedName("CANCELLED")
	CANCELLED,

	@SerializedName("DECLINED")
	DECLINED
}
