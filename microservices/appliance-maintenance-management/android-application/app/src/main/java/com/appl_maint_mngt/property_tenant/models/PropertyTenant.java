package com.appl_maint_mngt.property_tenant.models;

import com.appl_maint_mngt.property_tenant.models.constants.ResidenceStatus;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kyle on 19/03/2017.
 */

public class PropertyTenant extends APropertyTenant {

    private Long currentPropertyId;
    private List<Long> previousPropertyIds;

    @SerializedName("residenceStatus")
    private ResidenceStatus residenceStatus;

    @Override
    public Long getCurrentPropertyId() {
        return currentPropertyId;
    }

    @Override
    public void setCurrentPropertyId(Long currentPropertyId) {
        this.currentPropertyId = currentPropertyId;
    }

    @Override
    public List<Long> getPreviousPropertyIds() {
        return previousPropertyIds;
    }

    @Override
    public void setPreviousPropertyIds(List<Long> previousPropertyIds) {
        this.previousPropertyIds = previousPropertyIds;
    }

    @Override
    public ResidenceStatus getResidenceStatus() {
        return residenceStatus;
    }

    @Override
    public void setResidenceStatus(ResidenceStatus residenceStatus) {
        this.residenceStatus = residenceStatus;
    }
}
