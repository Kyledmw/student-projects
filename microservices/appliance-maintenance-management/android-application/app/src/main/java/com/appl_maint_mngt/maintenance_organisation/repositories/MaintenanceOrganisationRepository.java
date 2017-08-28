package com.appl_maint_mngt.maintenance_organisation.repositories;

import android.util.LongSparseArray;

import com.appl_maint_mngt.maintenance_organisation.models.MaintenanceOrganisation;
import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.maintenance_organisation.repositories.constants.IMaintenanceOrganisationObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 18/03/2017.
 */

public class MaintenanceOrganisationRepository extends AMaintenanceOrganisationRepository {

    private LongSparseArray<MaintenanceOrganisation> maintenanceOrganisations;

    public MaintenanceOrganisationRepository() {
        this.maintenanceOrganisations = new LongSparseArray<>();
    }

    @Override
    public void addItems(List<MaintenanceOrganisation> maintOrg) {
        for(MaintenanceOrganisation org: maintOrg) {
            maintenanceOrganisations.put(org.getId(), org);
        }
        updateObservers(IMaintenanceOrganisationObserverUpdateTypes.MODEL_UPDATE);
    }

    @Override
    public List<IMaintenanceOrganisationReadable> getAll() {
        List<IMaintenanceOrganisationReadable> list = new ArrayList<>();
        for(int i = 0; i < maintenanceOrganisations.size(); i++) {
            list.add(maintenanceOrganisations.valueAt(i));
        }
        return list;
    }

    @Override
    public IMaintenanceOrganisationReadable getForId(Long id) {
        return maintenanceOrganisations.get(id);
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
