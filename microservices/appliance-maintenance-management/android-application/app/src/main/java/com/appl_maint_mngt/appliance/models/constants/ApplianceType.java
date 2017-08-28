package com.appl_maint_mngt.appliance.models.constants;

import com.google.gson.annotations.SerializedName;

public enum ApplianceType {
	@SerializedName("COMMON")
	COMMON,

	@SerializedName("DISH_WASHER")
	DISH_WASHER,

	@SerializedName("FRIDGE_FREEZER")
	FRIDGE_FREEZER,

	@SerializedName("MICROWAVE")
	MICROWAVE,

	@SerializedName("OVEN")
	OVEN,

	@SerializedName("TUMBLE_DRYER")
	TUMBLE_DRYER,

	@SerializedName("WASHING_MACHINE")
	WASHING_MACHINE
}
