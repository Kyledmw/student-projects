package com.appl_maint_mngt.appliance_status.repositories.interfaces;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public interface IApplianceStatusReadableRepository {

    IApplianceStatusReadable getForId(Long id);

    List<IApplianceStatusReadable> getForApplianceType(ApplianceType type);
}
