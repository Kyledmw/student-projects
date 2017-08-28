package com.appl_maint_mngt.common.repositories;

import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryFactory;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryObserverHandler;

import java.util.Observer;

/**
 * Created by Kyle on 07/04/2017.
 */

public class RepositoryObserverHandler implements IRepositoryObserverHandler {

    private IRepositoryFactory repositoryFactory;

    public RepositoryObserverHandler(IRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public void observeAccountRepository(Observer observer) {
        repositoryFactory.createAccountRepository().addObserver(observer);
    }

    @Override
    public void observeMaintenanceEngineerRepository(Observer observer) {
        repositoryFactory.createMaintenanceEngineerRepository().addObserver(observer);
    }

    @Override
    public void observPropertyManagerRepository(Observer observer) {
        repositoryFactory.createPropertyManagerRepository().addObserver(observer);
    }

    @Override
    public void observePropertyTenantRepository(Observer observer) {
        repositoryFactory.createPropertyTenantRepository().addObserver(observer);
    }

    @Override
    public void observePropertyRepository(Observer observer) {
        repositoryFactory.createPropertyRepository().addObserver(observer);
    }

    @Override
    public void observePropertyApplianceRepository(Observer observer) {
        repositoryFactory.createPropertyApplianceRepository().addObserver(observer);
    }

    @Override
    public void observeApplianceStatusRepository(Observer observer) {
        repositoryFactory.createApplianceStatusRepository().addObserver(observer);
    }

    @Override
    public void observeApplianceRepository(Observer observer) {
        repositoryFactory.createApplianceRepository().addObserver(observer);
    }

    @Override
    public void observeDiagnosticReportRepository(Observer observer) {
        repositoryFactory.createDiagnosticReportRepository().addObserver(observer);
    }

    @Override
    public void observeMaintenanceOrganisationRepository(Observer observer) {
        repositoryFactory.createMaintenanceOrganisationRepository().addObserver(observer);
    }

    @Override
    public void observeDiagnosticRequestRepository(Observer observer) {
        repositoryFactory.createDiagnosticRequestRepository().addObserver(observer);
    }

    @Override
    public void observeRepairReportRepository(Observer observer) {
        repositoryFactory.createRepairReportRepository().addObserver(observer);
    }

    @Override
    public void observePendingRepairReportRepository(Observer observer) {
        repositoryFactory.createPendingRepairReportRepository().addObserver(observer);
    }

    @Override
    public void observeMaintenanceScheduleRepository(Observer observer) {
        repositoryFactory.createMaintenanceScheduleRepository().addObserver(observer);
    }

    @Override
    public void observePendingMaintenanceSchedulingRepository(Observer observer) {
        repositoryFactory.createPendingMaintenanceSchedulingRepository().addObserver(observer);
    }
}
