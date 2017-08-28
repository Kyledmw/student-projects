package com.appl_maint_mngt.common.repositories;

import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryFactory;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryUnObserveHandler;

import java.util.Observer;

/**
 * Created by Kyle on 07/04/2017.
 */

public class RepositoryUnObserveHandler implements IRepositoryUnObserveHandler {

    private IRepositoryFactory repositoryFactory;

    public RepositoryUnObserveHandler(IRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public void unObserveAccounntRepository(Observer observer) {
        repositoryFactory.createAccountRepository().deleteObserver(observer);
    }

    @Override
    public void unObserveMaintenanceEngineerRepository(Observer observer) {
        repositoryFactory.createMaintenanceEngineerRepository().deleteObserver(observer);
    }

    @Override
    public void unObservePropertyManangerRepository(Observer observer) {
        repositoryFactory.createPropertyManagerRepository().deleteObserver(observer);
    }

    @Override
    public void unObservePropertyTenantRepository(Observer observer) {
        repositoryFactory.createPropertyTenantRepository().deleteObserver(observer);
    }

    @Override
    public void unObservePropertyRepository(Observer observer) {
        repositoryFactory.createPropertyRepository().deleteObserver(observer);
    }

    @Override
    public void unObservePropertyApplianceRepository(Observer observer) {
        repositoryFactory.createPropertyApplianceRepository().deleteObserver(observer);
    }

    @Override
    public void unObserveApplianceStatusRepository(Observer observer) {
        repositoryFactory.createApplianceStatusRepository().deleteObserver(observer);
    }

    @Override
    public void unObserveApplianceRepository(Observer observer) {
        repositoryFactory.createApplianceRepository().deleteObserver(observer);
    }

    @Override
    public void unObserveDiagnosticReportRepository(Observer observer) {
        repositoryFactory.createDiagnosticReportRepository().deleteObserver(observer);
    }

    @Override
    public void unObserveMaintenanceOrganisationRepository(Observer observer) {
        repositoryFactory.createMaintenanceOrganisationRepository().deleteObserver(observer);
    }

    @Override
    public void unObserveDiagnosticRequestRepository(Observer observer) {
        repositoryFactory.createDiagnosticRequestRepository().deleteObserver(observer);
    }

    @Override
    public void unObserveRepairReportRepository(Observer observer) {
        repositoryFactory.createRepairReportRepository().deleteObserver(observer);
    }

    @Override
    public void unObservePendingRepairReportRepository(Observer observer) {
        repositoryFactory.createPendingRepairReportRepository().deleteObserver(observer);
    }

    @Override
    public void unObserveMaintenanceScheduleRepository(Observer observer) {
        repositoryFactory.createMaintenanceScheduleRepository().deleteObserver(observer);
    }

    @Override
    public void unObservePendingMaintenanceSchedulingRepository(Observer observer) {
        repositoryFactory.createPendingMaintenanceSchedulingRepository().deleteObserver(observer);
    }
}
