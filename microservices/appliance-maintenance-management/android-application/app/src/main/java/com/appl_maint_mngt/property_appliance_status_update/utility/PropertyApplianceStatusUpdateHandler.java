package com.appl_maint_mngt.property_appliance_status_update.utility;

import android.app.Activity;
import android.content.DialogInterface;

import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.appliance.models.interfaces.IApplianceReadable;
import com.appl_maint_mngt.appliance_status.models.constants.StatusType;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.appliance_status.utility.ApplianceStatusRetriever;
import com.appl_maint_mngt.appliance_status.utility.interfaces.IApplianceStatusRetriever;
import com.appl_maint_mngt.common.errors.DialogErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance_status_update.models.web.PropertyApplianceStatusUpdatePayload;
import com.appl_maint_mngt.property_appliance_status_update.models.web.interfaces.IPropertyApplianceStatusUpdatePayload;
import com.appl_maint_mngt.property_appliance_status_update.utility.interfaces.IPropertyApplianceStatusUpdateHandler;
import com.appl_maint_mngt.property_appliance_status_update.views.UpdatePropertyApplianceStatusDialog;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyApplianceStatusUpdateHandler implements IPropertyApplianceStatusUpdateHandler {

    private Activity parent;

    public PropertyApplianceStatusUpdateHandler(Activity parent) {
        this.parent = parent;
    }

    @Override
    public void handle(IPropertyApplianceReadable propertyAppliance) {
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        IApplianceStatusReadable applianceStatus = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getApplianceStatusRepository().getForId(propertyAppliance.getStatusId());
        IApplianceReadable appliance = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getApplianceRepository().get(propertyAppliance.getApplianceId());
        IApplianceStatusRetriever applianceStatusRetriever = new ApplianceStatusRetriever();
        if(applianceStatus.getType().equals(StatusType.OKAY)) {
            if(account.getUserType().equals(UserType.PROPERTY_TENANT)) {
                List<IApplianceStatusReadable> applianceStatusList = applianceStatusRetriever.getBesidesOKAndRepairing(appliance.getType());
                display(applianceStatusList, propertyAppliance);
            }
        } else if(!applianceStatus.getType().equals(StatusType.OKAY) && !applianceStatus.getType().equals(StatusType.REPAIRING)) {
            if(account.getUserType().equals(UserType.PROPERTY_TENANT)) {
                List<IApplianceStatusReadable> applianceStatusList = applianceStatusRetriever.getBesidesOKAndRepairing(appliance.getType());
                display(applianceStatusList, propertyAppliance);
            }
        } else if(applianceStatus.getType().equals(StatusType.REPAIRING)) {
            if(!account.getUserType().equals(UserType.PROPERTY_TENANT)) {
                List<IApplianceStatusReadable> applianceStatusList = applianceStatusRetriever.getBesidesRepairing(appliance.getType());
                display(applianceStatusList, propertyAppliance);
            }
        }
    }

    private void display(List<IApplianceStatusReadable> applianceStatusList, final IPropertyApplianceReadable propertyAppliance) {
        final UpdatePropertyApplianceStatusDialog dialog = new UpdatePropertyApplianceStatusDialog(parent, applianceStatusList);
        dialog.setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int which) {
                IApplianceStatusReadable applianceStatus = dialog.getSelected();
                IPropertyApplianceStatusUpdatePayload payload = new PropertyApplianceStatusUpdatePayload();
                payload.setPropertyApplianceId(propertyAppliance.getId());
                payload.setNewApplianceStatusId(applianceStatus.getId());
                IntegrationController.getInstance().getControllerFactory().createPropertyApplianceStatusUpdateController().updateStatus(payload, new DialogErrorCallback(parent));
                dialog.close();
            }
        });
        dialog.create();
        dialog.show();
    }
}
