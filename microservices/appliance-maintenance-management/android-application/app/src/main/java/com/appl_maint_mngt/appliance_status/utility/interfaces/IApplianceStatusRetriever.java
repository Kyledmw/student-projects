package com.appl_maint_mngt.appliance_status.utility.interfaces;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IApplianceStatusRetriever {

    List<IApplianceStatusReadable> getBesidesOKAndRepairing(ApplianceType applianceType);
    List<IApplianceStatusReadable> getBesidesRepairing(ApplianceType applianceType);

}
