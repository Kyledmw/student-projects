package com.appl_maint_mngt.common.repositories;

import com.appl_maint_mngt.account.repositories.interfaces.IAccountUpdateableRepository;
import com.appl_maint_mngt.appliance.repositories.interfaces.IApplianceUpdateableRepository;
import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusUpdateableRepository;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryFactory;
import com.appl_maint_mngt.common.repositories.interfaces.IUpdateableRepositoryRetriever;
import com.appl_maint_mngt.diagnostic_report.repositories.interfaces.IDiagnosticReportUpdateableRepository;
import com.appl_maint_mngt.diagnostic_request.repositories.interfaces.IDiagnosticRequestUpdateableRepository;
import com.appl_maint_mngt.maintenance_engineer.repositories.interfaces.IMaintenanceEngineerUpdateableRepository;
import com.appl_maint_mngt.maintenance_organisation.repositories.interfaces.IMaintenanceOrganisationUpdateableRepository;
import com.appl_maint_mngt.maintenance_schedule.repositories.interfaces.IMaintenanceScheduleUpdateableRepository;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.interfaces.IPendingMaintenanceSchedulingUpdateableRepository;
import com.appl_maint_mngt.pending_repair_report.repositories.interfaces.IPendingRepairReportUpdateableRepository;
import com.appl_maint_mngt.property.repositories.interfaces.IPropertyUpdateableRepository;
import com.appl_maint_mngt.property_appliance.repositories.interfaces.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.property_manager.repositories.interfaces.IPropertyManagerUpdateableRepository;
import com.appl_maint_mngt.property_tenant.repositories.interfaces.IPropertyTenantUpdateableRepository;
import com.appl_maint_mngt.repair_report.repositories.interfaces.IRepairReportUpdateableRepository;

/**
 * Created by Kyle on 07/04/2017.
 */

public class UpdateableRepositoryRetriever implements IUpdateableRepositoryRetriever {

    private IRepositoryFactory repositoryFactory;

    public UpdateableRepositoryRetriever(IRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public IAccountUpdateableRepository getAccountRepository() {
        return repositoryFactory.createAccountRepository();
    }

    @Override
    public IMaintenanceEngineerUpdateableRepository getMaintenanceEngineerRepository() {
        return repositoryFactory.createMaintenanceEngineerRepository();
    }

    @Override
    public IPropertyManagerUpdateableRepository getPropertyManagerRepository() {
        return repositoryFactory.createPropertyManagerRepository();
    }

    @Override
    public IPropertyTenantUpdateableRepository getPropertyTenantRepository() {
        return repositoryFactory.createPropertyTenantRepository();
    }

    @Override
    public IPropertyUpdateableRepository getPropertyRepository() {
        return repositoryFactory.createPropertyRepository();
    }

    @Override
    public IPropertyApplianceUpdateableRepository getPropertyApplianceRepository() {
        return repositoryFactory.createPropertyApplianceRepository();
    }

    @Override
    public IApplianceStatusUpdateableRepository getApplianceStatusRepository() {
        return repositoryFactory.createApplianceStatusRepository();
    }

    @Override
    public IApplianceUpdateableRepository getApplianceRepository() {
        return repositoryFactory.createApplianceRepository();
    }

    @Override
    public IDiagnosticReportUpdateableRepository getDiagnosticReportRepository() {
        return repositoryFactory.createDiagnosticReportRepository();
    }

    @Override
    public IMaintenanceOrganisationUpdateableRepository getMaintenanceOrganisationRepository() {
        return repositoryFactory.createMaintenanceOrganisationRepository();
    }

    @Override
    public IDiagnosticRequestUpdateableRepository getDiagnosticRequestRepository() {
        return repositoryFactory.createDiagnosticRequestRepository();
    }

    @Override
    public IRepairReportUpdateableRepository getRepairReportRepository() {
        return repositoryFactory.createRepairReportRepository();
    }

    @Override
    public IPendingRepairReportUpdateableRepository getPendingRepairReportRepository() {
        return repositoryFactory.createPendingRepairReportRepository();
    }

    @Override
    public IMaintenanceScheduleUpdateableRepository getMaintenanceScheduleRepository() {
        return repositoryFactory.createMaintenanceScheduleRepository();
    }

    @Override
    public IPendingMaintenanceSchedulingUpdateableRepository getPendingMaintenanceSchedulingRepository() {
        return repositoryFactory.createPendingMaintenanceSchedulingRepository();
    }
}
