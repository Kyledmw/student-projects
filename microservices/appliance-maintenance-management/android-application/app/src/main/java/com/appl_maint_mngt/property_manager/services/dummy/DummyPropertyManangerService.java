package com.appl_maint_mngt.property_manager.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_manager.models.PropertyManager;
import com.appl_maint_mngt.property_manager.repositories.interfaces.IPropertyManagerUpdateableRepository;
import com.appl_maint_mngt.property_manager.services.interfaces.IPropertyManagerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 07/04/2017.
 */

public class DummyPropertyManangerService implements IPropertyManagerService {
    @Override
    public void get(Long accountId, IErrorCallback errorCallback) {
        IPropertyManagerUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyManagerRepository();
        PropertyManager propertyManager = new PropertyManager();
        List<Long> current = new ArrayList<>();
        current.add((long) 1);
        propertyManager.setCurrentPropertyIds(current);
        propertyManager.setPreviousPropertyIds(new ArrayList<Long>());
        repository.update(propertyManager);
    }
}
