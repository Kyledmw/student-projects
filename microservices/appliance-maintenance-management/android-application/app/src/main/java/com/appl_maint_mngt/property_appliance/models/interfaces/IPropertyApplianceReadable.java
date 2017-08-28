package com.appl_maint_mngt.property_appliance.models.interfaces;


import com.appl_maint_mngt.status_history.models.interfaces.IStatusHistoryReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public interface IPropertyApplianceReadable {

    Long getId();

    String getApplianceId();

    Long getPropertyId();

    Long getStatusId();

    String getName();

    int getAge();

    List<IStatusHistoryReadable> getStatusHistory();
}
