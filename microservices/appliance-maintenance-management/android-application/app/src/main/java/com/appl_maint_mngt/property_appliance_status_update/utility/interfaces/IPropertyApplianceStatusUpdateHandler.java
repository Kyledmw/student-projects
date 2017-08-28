package com.appl_maint_mngt.property_appliance_status_update.utility.interfaces;

import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IPropertyApplianceStatusUpdateHandler {

    void handle(IPropertyApplianceReadable propertyAppliance);
}
