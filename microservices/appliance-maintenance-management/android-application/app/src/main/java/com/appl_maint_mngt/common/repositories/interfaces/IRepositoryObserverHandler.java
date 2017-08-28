package com.appl_maint_mngt.common.repositories.interfaces;

import java.util.Observer;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IRepositoryObserverHandler {

    void observeAccountRepository(Observer observer);
    void observeMaintenanceEngineerRepository(Observer observer);
    void observPropertyManagerRepository(Observer observer);
    void observePropertyTenantRepository(Observer observer);
    void observePropertyRepository(Observer observer);
    void observePropertyApplianceRepository(Observer observer);
    void observeApplianceStatusRepository(Observer observer);
    void observeApplianceRepository(Observer observer);
    void observeDiagnosticReportRepository(Observer observer);
    void observeMaintenanceOrganisationRepository(Observer observer);
    void observeDiagnosticRequestRepository(Observer observer);
    void observeRepairReportRepository(Observer observer);
    void observePendingRepairReportRepository(Observer observer);
    void observeMaintenanceScheduleRepository(Observer observer);
    void observePendingMaintenanceSchedulingRepository(Observer observer);
}
