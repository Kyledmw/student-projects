package com.appl_maint_mngt.property_appliance_status_update.models.web;

import com.appl_maint_mngt.property_appliance_status_update.models.web.interfaces.IPropertyApplianceStatusUpdatePayload;

/**
 * Created by Kyle on 09/04/2017.
 */

public class PropertyApplianceStatusUpdatePayload implements IPropertyApplianceStatusUpdatePayload {

    private Long propertyApplianceId;

    private Long newApplianceStatusId;

    public Long getPropertyApplianceId() {
        return propertyApplianceId;
    }

    public Long getNewApplianceStatusId() {
        return newApplianceStatusId;
    }

    public void setPropertyApplianceId(Long id) {
        this.propertyApplianceId= id;
    }

    public void setNewApplianceStatusId(Long id) {
        this.newApplianceStatusId = id;
    }
}
