package com.appl_maint_mngt.maintenance_engineer.models.interfaces;

import java.util.List;

/**
 * Created by Kyle on 19/03/2017.
 */

public interface IMaintenanceEngineerWriteable {
    void setCurrentOrganisationId(Long currentOrganisationId);
    void setPreviousOrganisationIds(List<Long> previousOrganisationIds);
}
