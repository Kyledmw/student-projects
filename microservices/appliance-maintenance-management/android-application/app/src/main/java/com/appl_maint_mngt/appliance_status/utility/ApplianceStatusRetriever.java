package com.appl_maint_mngt.appliance_status.utility;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance_status.models.constants.StatusType;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.appliance_status.utility.interfaces.IApplianceStatusRetriever;
import com.appl_maint_mngt.common.integration.IntegrationController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class ApplianceStatusRetriever implements IApplianceStatusRetriever {

    @Override
    public List<IApplianceStatusReadable> getBesidesOKAndRepairing(ApplianceType applianceType) {
        List<IApplianceStatusReadable> list = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getApplianceStatusRepository().getForApplianceType(applianceType);
        List<IApplianceStatusReadable> filtered = new ArrayList<>();
        for(IApplianceStatusReadable applStat: list) {
            if(!applStat.getType().equals(StatusType.OKAY) && !applStat.getType().equals(StatusType.REPAIRING)) filtered.add(applStat);
        }
        return filtered;
    }

    @Override
    public List<IApplianceStatusReadable> getBesidesRepairing(ApplianceType applianceType) {
        List<IApplianceStatusReadable> list = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getApplianceStatusRepository().getForApplianceType(applianceType);
        List<IApplianceStatusReadable> filtered = new ArrayList<>();
        for(IApplianceStatusReadable applStat: list) {
            if(!applStat.getType().equals(StatusType.REPAIRING)) filtered.add(applStat);
        }
        return filtered;
    }
}
