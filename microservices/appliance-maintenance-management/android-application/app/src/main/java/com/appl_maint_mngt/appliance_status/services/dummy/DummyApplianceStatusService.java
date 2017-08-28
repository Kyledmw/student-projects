package com.appl_maint_mngt.appliance_status.services.dummy;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance_status.models.ApplianceStatus;
import com.appl_maint_mngt.appliance_status.models.constants.StatusType;
import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusUpdateableRepository;
import com.appl_maint_mngt.appliance_status.services.interfaces.IApplianceStatusService;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class DummyApplianceStatusService implements IApplianceStatusService {
    @Override
    public void get(IErrorCallback errorCallback) {
        IApplianceStatusUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getApplianceStatusRepository();
        List<ApplianceStatus> applianceStatusList = new ArrayList<>();

        ApplianceStatus applianceStatus1 = new ApplianceStatus();
        applianceStatus1.setId((long) 1);
        applianceStatus1.setApplianceType(ApplianceType.COMMON);
        applianceStatus1.setIconUrl("http://placehold.it/32x32");
        applianceStatus1.setMessage("Message");
        applianceStatus1.setType(StatusType.OKAY);
        applianceStatusList.add(applianceStatus1);

        ApplianceStatus applianceStatus2 = new ApplianceStatus();
        applianceStatus2.setId((long) 2);
        applianceStatus2.setApplianceType(ApplianceType.COMMON);
        applianceStatus2.setIconUrl("http://placehold.it/32x32");
        applianceStatus2.setMessage("Message");
        applianceStatus2.setType(StatusType.REPAIRING);
        applianceStatusList.add(applianceStatus2);

        ApplianceStatus applianceStatus3 = new ApplianceStatus();
        applianceStatus3.setId((long) 3);
        applianceStatus3.setApplianceType(ApplianceType.COMMON);
        applianceStatus3.setIconUrl("http://placehold.it/32x32");
        applianceStatus3.setMessage("Message");
        applianceStatus3.setType(StatusType.MALFUNCTION);
        applianceStatusList.add(applianceStatus3);

        repository.addItems(applianceStatusList);
    }
}
