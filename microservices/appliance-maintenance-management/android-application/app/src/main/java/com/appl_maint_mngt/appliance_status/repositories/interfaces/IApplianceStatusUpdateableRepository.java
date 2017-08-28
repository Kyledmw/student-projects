package com.appl_maint_mngt.appliance_status.repositories.interfaces;


import com.appl_maint_mngt.appliance_status.models.ApplianceStatus;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IApplianceStatusUpdateableRepository {

    void addItem(ApplianceStatus status);

    void addItems(List<ApplianceStatus> statuses);

    void addItems(List<ApplianceStatus> statuses, String updateType);
}
