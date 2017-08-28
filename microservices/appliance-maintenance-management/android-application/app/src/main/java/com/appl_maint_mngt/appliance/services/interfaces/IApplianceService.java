package com.appl_maint_mngt.appliance.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IApplianceService {

    void get(String id, IErrorCallback errorCallback);

    void get(IErrorCallback errorCallback);
}
