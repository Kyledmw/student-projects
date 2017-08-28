package com.appl_maint_mngt.property_appliance_status_update.views.interfaces;

import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.common.views.interfaces.ICommonDialog;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IUpdatePropertyApplianceStatusDialog extends ICommonDialog {
    IApplianceStatusReadable getSelected();
}
