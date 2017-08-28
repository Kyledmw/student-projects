package com.appl_maint_mngt.appliance_status.models.constants;

import com.google.gson.annotations.SerializedName;

public enum StatusType {
	@SerializedName("OKAY")
	OKAY,

	@SerializedName("REPAIRING")
	REPAIRING,

	@SerializedName("IRREPAIRABLE")
	IRREPAIRABLE,

	@SerializedName("MALFUNCTION")
	MALFUNCTION,

	@SerializedName("LEAK")
	LEAK,

	@SerializedName("NOT_COOLING")
	NOT_COOLING,

	@SerializedName("DOOR_WONT_CLOSE")
	DOOR_WONT_CLOSE,

	@SerializedName("LIGHT_FAULT")
	LIGHT_FAULT,

	@SerializedName("RUST")
	RUST,

	@SerializedName("WONT_TURN_ON")
	WONT_TURN_ON,

	@SerializedName("PUMP_FAULT")
	PUMP_FAULT,

	@SerializedName("WONT_SPIN")
	WONT_SPIN,

	@SerializedName("NOT_HEATING")
	NOT_HEATING
}
