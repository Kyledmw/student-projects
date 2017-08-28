package com.appl_maint_mngt.common.integration.interfaces;

import com.appl_maint_mngt.common.controllers.interfaces.IControllerFactory;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryController;
import com.appl_maint_mngt.common.services.interfaces.IServiceFactory;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IIntegrator {

    IRepositoryController getRepositoryController();
    IControllerFactory getControllerFactory();
    IServiceFactory getServiceFactory();
}
