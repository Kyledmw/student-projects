package com.appl_maint_mngt.appliance_status.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IApplianceStatusService {

    void get(IErrorCallback errorCallback);
}
