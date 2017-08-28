package com.appl_maint_mngt.common.repositories.interfaces;

import java.util.Observer;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IRepositoryUnObserveHandler {
    void unObserveAccounntRepository(Observer observer);
    void unObserveMaintenanceEngineerRepository(Observer observer);
    void unObservePropertyManangerRepository(Observer observer);
    void unObservePropertyTenantRepository(Observer observer);
    void unObservePropertyRepository(Observer observer);
    void unObservePropertyApplianceRepository(Observer observer);
    void unObserveApplianceStatusRepository(Observer observer);
    void unObserveApplianceRepository(Observer observer);
    void unObserveDiagnosticReportRepository(Observer observer);
    void unObserveMaintenanceOrganisationRepository(Observer observer);
    void unObserveDiagnosticRequestRepository(Observer observer);
    void unObserveRepairReportRepository(Observer observer);
    void unObservePendingRepairReportRepository(Observer observer);
    void unObserveMaintenanceScheduleRepository(Observer observer);
    void unObservePendingMaintenanceSchedulingRepository(Observer observer);
}
