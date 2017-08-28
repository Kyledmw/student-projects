package com.appl_maint_mngt.appliance_status.models.interfaces;


import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance_status.models.constants.StatusType;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceStatusReadable {
    Long getId();
    StatusType getType();
    String getMessage();
    String getIconUrl();
    ApplianceType getApplianceType();
}
