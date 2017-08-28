package com.appl_maint_mngt.maintenance_organisation.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 09/04/2017.
 */

public interface IMaintenanceOrganisationController {

    void getAll(IErrorCallback callback);
}
