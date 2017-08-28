package com.appl_maint_mngt.appliance_status.models.interfaces;


import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance_status.models.constants.StatusType;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceStatusWriteable {
    void setId(Long id);
    void setType(StatusType type);
    void setMessage(String message);
    void setIconUrl(String iconUrl);
    void setApplianceType(ApplianceType applianceType);
}
