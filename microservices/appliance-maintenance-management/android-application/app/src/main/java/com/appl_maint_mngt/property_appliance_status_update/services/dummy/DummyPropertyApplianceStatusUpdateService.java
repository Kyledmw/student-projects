package com.appl_maint_mngt.property_appliance_status_update.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.property_appliance_status_update.events.constants.IPropertyApplianceStatusUpdateEvents;
import com.appl_maint_mngt.property_appliance_status_update.models.web.interfaces.IPropertyApplianceStatusUpdatePayload;
import com.appl_maint_mngt.property_appliance_status_update.services.interfaces.IPropertyApplianceStatusUpdateService;

/**
 * Created by Kyle on 09/04/2017.
 */

public class DummyPropertyApplianceStatusUpdateService implements IPropertyApplianceStatusUpdateService {
    @Override
    public void update(IPropertyApplianceStatusUpdatePayload payload, IErrorCallback errorCallback) {
        ApplicationEventBus.getInstance().sendEvent(IPropertyApplianceStatusUpdateEvents.SUCCESS_EVENT);
    }
}
