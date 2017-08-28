package com.appl_maint_mngt.common.integration;

import com.appl_maint_mngt.common.controllers.ControllerFactory;
import com.appl_maint_mngt.common.controllers.interfaces.IControllerFactory;
import com.appl_maint_mngt.common.integration.interfaces.IIntegrator;
import com.appl_maint_mngt.common.repositories.RepositoryController;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryController;
import com.appl_maint_mngt.common.services.ServiceFactory;
import com.appl_maint_mngt.common.services.dummy.DummyServiceFactory;
import com.appl_maint_mngt.common.services.interfaces.IServiceFactory;

/**
 * Created by Kyle on 07/04/2017.
 */

public class IntegrationController implements IIntegrator {
    private static IntegrationController instance;

    private IRepositoryController repositoryController;
    private IServiceFactory serviceFactory;
    private IControllerFactory controllerFactory;

    public static IntegrationController getInstance() {
        if(instance == null) instance = new IntegrationController();
        return instance;
    }

    private IntegrationController() {
        repositoryController = new RepositoryController();
        serviceFactory = new ServiceFactory();
        controllerFactory = new ControllerFactory();
    }

    @Override
    public IRepositoryController getRepositoryController() {
        return repositoryController;
    }

    @Override
    public IControllerFactory getControllerFactory() {
        return controllerFactory;
    }

    @Override
    public IServiceFactory getServiceFactory() {
        return serviceFactory;
    }
}
