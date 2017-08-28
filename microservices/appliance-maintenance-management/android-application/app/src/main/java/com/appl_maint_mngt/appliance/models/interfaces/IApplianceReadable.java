package com.appl_maint_mngt.appliance.models.interfaces;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;

/**
 * Created by Kyle on 18/03/2017.
 */

public interface IApplianceReadable {

    String getId();
    String getProductNo();
    String getName();
    String getBrand();
    ApplianceType getType();
}
