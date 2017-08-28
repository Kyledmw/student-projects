package com.appl_maint_mngt.maintenance_organisation.utility;

import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.maintenance_organisation.utility.interfaces.IMaintenanceOrganisationListFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class MaintenanceOrganisationListFilter implements IMaintenanceOrganisationListFilter {
    @Override
    public List<IMaintenanceOrganisationReadable> filterIds(List<IMaintenanceOrganisationReadable> maintOrgs, List<Long> filterIDs) {
        List<IMaintenanceOrganisationReadable> filteredList = new ArrayList<>();
        for(IMaintenanceOrganisationReadable maintOrg : maintOrgs) {
            boolean matched = false;
            for(Long idToFilter: filterIDs) {
                if(maintOrg.getId().equals(idToFilter)) {
                    matched = true;
                    break;
                }
            }
            if(!matched){
                filteredList.add(maintOrg);
            }
        }
        return filteredList;
    }
}
