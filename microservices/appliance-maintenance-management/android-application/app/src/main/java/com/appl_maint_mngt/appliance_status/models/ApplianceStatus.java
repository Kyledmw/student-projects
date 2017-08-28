package com.appl_maint_mngt.appliance_status.models;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance_status.models.constants.StatusType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyle on 18/03/2017.
 */
public class ApplianceStatus extends AApplianceStatus {

    private Long id;

    @SerializedName("type")
    private StatusType type;

    private String message;

    private String iconUrl;

    @SerializedName("applianceType")
    private ApplianceType applianceType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public StatusType getType() {
        return type;
    }

    @Override
    public void setType(StatusType type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getIconUrl() {
        return iconUrl;
    }

    @Override
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public ApplianceType getApplianceType() {
        return applianceType;
    }

    @Override
    public void setApplianceType(ApplianceType applianceType) {
        this.applianceType = applianceType;
    }

    public String toString() {
        return type.toString();
    }
}
