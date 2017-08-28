package com.appl_maint_mngt.property_manager.repositories;

import com.appl_maint_mngt.property_manager.models.APropertyManager;
import com.appl_maint_mngt.property_manager.models.PropertyManager;
import com.appl_maint_mngt.property_manager.models.interfaces.IPropertyManagerReadable;
import com.appl_maint_mngt.property_manager.repositories.constants.IPropertyManagerObserverUpdateTypes;

/**
 * Created by Kyle on 17/03/2017.
 */

public class PropertyManagerRepository extends APropertyManagerRepository {

    private APropertyManager propertyManager;

    public PropertyManagerRepository() {
        propertyManager = new PropertyManager();
    }

    @Override
    public void update(APropertyManager propertyManager) {
        this.propertyManager.setCurrentPropertyIds(propertyManager.getCurrentPropertyIds());
        this.propertyManager.setPreviousPropertyIds(propertyManager.getPreviousPropertyIds());
        updateObservers(IPropertyManagerObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public IPropertyManagerReadable get() {
        return propertyManager;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
