package com.appl_maint_mngt.property.repositories;


import android.util.LongSparseArray;


import com.appl_maint_mngt.property.models.AProperty;
import com.appl_maint_mngt.property.models.Property;
import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;
import com.appl_maint_mngt.property.repositories.constants.IPropertyObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public class PropertyRepository extends APropertyRepository {

    private LongSparseArray<AProperty> properties;

    public PropertyRepository() {
        properties = new LongSparseArray<>();
    }

    @Override
    public List<IPropertyReadable> getAll() {
        List<IPropertyReadable> readableList = new ArrayList<>();
        for(int i = 0; i < properties.size(); i++) {
            readableList.add(properties.valueAt(i));
        }
        return readableList;
    }

    @Override
    public IPropertyReadable getForId(Long id) {
        return properties.get(id);
    }

    @Override
    public void addItem(AProperty property) {
        properties.put(property.getId(), property);
        updateObservers(IPropertyObserverUpdateTypes.NEW_ITEM_UPDATE);
    }

    @Override
    public void addItems(List<Property> properties) {
        for(Property prop: properties) {
            this.properties.put(prop.getId(), prop);
        }
        updateObservers(IPropertyObserverUpdateTypes.NEW_ITEM_UPDATE);
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
