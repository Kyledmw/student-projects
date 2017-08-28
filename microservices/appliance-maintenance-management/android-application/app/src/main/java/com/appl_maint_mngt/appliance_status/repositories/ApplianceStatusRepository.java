package com.appl_maint_mngt.appliance_status.repositories;

import android.util.LongSparseArray;

import com.appl_maint_mngt.appliance.models.constants.ApplianceType;
import com.appl_maint_mngt.appliance_status.models.ApplianceStatus;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.appliance_status.repositories.constants.IApplianceStatusObserverUpdateTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public class ApplianceStatusRepository extends AApplianceStatusRepository {

    private LongSparseArray<ApplianceStatus> applianceStatuses;

    public ApplianceStatusRepository() {
        applianceStatuses = new LongSparseArray<>();
    }

    @Override
    public void addItem(ApplianceStatus status) {
        applianceStatuses.put(status.getId(), status);
        updateObservers(IApplianceStatusObserverUpdateTypes.CURRENT_STATUS_UPDATE);
    }

    @Override
    public void addItems(List<ApplianceStatus> statuses) {
        for(ApplianceStatus status: statuses) {
            applianceStatuses.put(status.getId(), status);
        }
        updateObservers(IApplianceStatusObserverUpdateTypes.STATUS_HISTORY_UPDATE);
    }

    @Override
    public void addItems(List<ApplianceStatus> statuses, String updateType) {
        for(ApplianceStatus status: statuses) {
            applianceStatuses.put(status.getId(), status);
        }
        updateObservers(updateType);
    }

    @Override
    public IApplianceStatusReadable getForId(Long id) {
        return applianceStatuses.get(id);
    }

    @Override
    public List<IApplianceStatusReadable> getForApplianceType(ApplianceType type) {
        List<IApplianceStatusReadable> list = new ArrayList<>();
        for(int i = 0; i< applianceStatuses.size(); i++) {
            IApplianceStatusReadable stat = applianceStatuses.valueAt(i);
            if(stat.getApplianceType().equals(type) || stat.getApplianceType().equals(ApplianceType.COMMON)) list.add(stat);
        }
        return list;
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
