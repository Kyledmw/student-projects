package com.appl_maint_mngt.maintenance_engineer.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IMaintenanceEngineerController {

    void getEngineer(Long accountId, IErrorCallback errorCallback);
}
