package com.appl_maint_mngt.property_appliance.repositories;

import android.util.LongSparseArray;

import com.appl_maint_mngt.property_appliance.models.APropertyAppliance;
import com.appl_maint_mngt.property_appliance.models.PropertyAppliance;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.repositories.constants.IPropertyApplianceObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */
public class PropertyApplianceRepository extends APropertyApplianceRepository {

    private LongSparseArray<APropertyAppliance> propertyAppliances;

    public PropertyApplianceRepository() {
        propertyAppliances = new LongSparseArray<>();
    }

    @Override
    public void addItems(List<PropertyAppliance> propertyAppliances) {
        for(PropertyAppliance pAppl: propertyAppliances) {
            this.propertyAppliances.put(pAppl.getId(), pAppl);
        }
        updateObservers(IPropertyApplianceObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public void addItem(PropertyAppliance propertyAppliance) {
        propertyAppliances.put(propertyAppliance.getId(), propertyAppliance);
    }

    @Override
    public IPropertyApplianceReadable getForId(Long id) {
        return propertyAppliances.get(id);
    }

    @Override
    public List<IPropertyApplianceReadable> getForPropertyId(Long id) {
        List<IPropertyApplianceReadable> readablesList = new ArrayList<>();
        for(int i = 0; i < this.propertyAppliances.size(); i++) {
            if(propertyAppliances.valueAt(i).getPropertyId().equals(id)) {
                readablesList.add(propertyAppliances.valueAt(i));
            }
        }
        return readablesList;
    }

    @Override
    public List<IPropertyApplianceReadable> getAll() {
        List<IPropertyApplianceReadable> readablesList = new ArrayList<>();
        for(int i = 0; i < this.propertyAppliances.size(); i++) {
            readablesList.add(propertyAppliances.valueAt(i));
        }
        return readablesList;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
