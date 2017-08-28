package com.appl_maint_mngt.property_tenant.models.constants;

import com.google.gson.annotations.SerializedName;

public enum ResidenceStatus {

	@SerializedName("OCCUPANT")
	OCCUPANT,

	@SerializedName("PENDING")
	PENDING,

	@SerializedName("NO_RESIDENCE")
	NO_RESIDENCE
}
