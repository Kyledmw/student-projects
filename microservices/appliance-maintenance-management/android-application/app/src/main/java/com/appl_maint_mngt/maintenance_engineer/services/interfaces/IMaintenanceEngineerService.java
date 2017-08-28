package com.appl_maint_mngt.maintenance_engineer.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IMaintenanceEngineerService {

    void get(Long id, IErrorCallback errorCallback);
}
