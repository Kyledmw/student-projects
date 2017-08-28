package com.appl_maint_mngt.property_appliance_status_update.models.web.interfaces;

/**
 * Created by Kyle on 09/04/2017.
 */

public interface IPropertyApplianceStatusUpdatePayload {
    Long getPropertyApplianceId();

    Long getNewApplianceStatusId();

    void setPropertyApplianceId(Long id);

    void setNewApplianceStatusId(Long id);
}
