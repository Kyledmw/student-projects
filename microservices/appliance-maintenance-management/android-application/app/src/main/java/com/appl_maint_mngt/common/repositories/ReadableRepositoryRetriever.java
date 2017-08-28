package com.appl_maint_mngt.common.repositories;

import com.appl_maint_mngt.account.repositories.interfaces.IAccountReadableRepository;
import com.appl_maint_mngt.appliance.repositories.interfaces.IApplianceReadableRepository;
import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.common.repositories.interfaces.IReadableRepositoryRetriever;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryFactory;
import com.appl_maint_mngt.diagnostic_report.repositories.interfaces.IDiagnosticReportReadableRepository;
import com.appl_maint_mngt.diagnostic_request.repositories.interfaces.IDiagnosticRequestReadableRepository;
import com.appl_maint_mngt.maintenance_engineer.repositories.interfaces.IMaintenanceEngineerReadableRepository;
import com.appl_maint_mngt.maintenance_organisation.repositories.interfaces.IMaintenanceOrganisationReadableRepository;
import com.appl_maint_mngt.maintenance_schedule.repositories.interfaces.IMaintenanceScheduleReadableRepository;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.interfaces.IPendingMaintenanceSchedulingReadableRepository;
import com.appl_maint_mngt.pending_repair_report.repositories.interfaces.IPendingRepairReportReadableRepository;
import com.appl_maint_mngt.property.repositories.interfaces.IPropertyReadableRepository;
import com.appl_maint_mngt.property_appliance.repositories.interfaces.IPropertyApplianceReadableRepository;
import com.appl_maint_mngt.property_manager.repositories.interfaces.IPropertyManagerReadableRepository;
import com.appl_maint_mngt.property_tenant.repositories.interfaces.IPropertyTenantReadableRepository;
import com.appl_maint_mngt.repair_report.repositories.interfaces.IRepairReportReadableRepository;

/**
 * Created by Kyle on 07/04/2017.
 */

public class ReadableRepositoryRetriever implements IReadableRepositoryRetriever {

    private IRepositoryFactory repositoryFactory;

    public ReadableRepositoryRetriever(IRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public IAccountReadableRepository getAccountRepository() {
        return repositoryFactory.createAccountRepository();
    }

    @Override
    public IMaintenanceEngineerReadableRepository getMaintenanceEngineerRepository() {
        return repositoryFactory.createMaintenanceEngineerRepository();
    }

    @Override
    public IPropertyManagerReadableRepository getPropertyManangerRepository() {
        return repositoryFactory.createPropertyManagerRepository();
    }

    @Override
    public IPropertyTenantReadableRepository getPropertyTenantRepository() {
        return repositoryFactory.createPropertyTenantRepository();
    }

    @Override
    public IPropertyReadableRepository getPropertyRepository() {
        return repositoryFactory.createPropertyRepository();
    }

    @Override
    public IPropertyApplianceReadableRepository getPropertyApplianceRepository() {
        return repositoryFactory.createPropertyApplianceRepository();
    }

    @Override
    public IApplianceStatusReadableRepository getApplianceStatusRepository() {
        return repositoryFactory.createApplianceStatusRepository();
    }

    @Override
    public IApplianceReadableRepository getApplianceRepository() {
        return repositoryFactory.createApplianceRepository();
    }

    @Override
    public IDiagnosticReportReadableRepository getDiagnosticReportRepository() {
        return repositoryFactory.createDiagnosticReportRepository();
    }

    @Override
    public IMaintenanceOrganisationReadableRepository getMaintenanceOrganisationRepository() {
        return repositoryFactory.createMaintenanceOrganisationRepository();
    }

    @Override
    public IDiagnosticRequestReadableRepository getDiagnosticRequestRepository() {
        return repositoryFactory.createDiagnosticRequestRepository();
    }

    @Override
    public IRepairReportReadableRepository getRepairReportReadableRepository() {
        return repositoryFactory.createRepairReportRepository();
    }

    @Override
    public IPendingRepairReportReadableRepository getPendingRepairReportReadableRepository() {
        return repositoryFactory.createPendingRepairReportRepository();
    }

    @Override
    public IMaintenanceScheduleReadableRepository getMaintenanceScheduleReadableRepository() {
        return repositoryFactory.createMaintenanceScheduleRepository();
    }

    @Override
    public IPendingMaintenanceSchedulingReadableRepository getPendingMaintenanceSchedulingReadableRepository() {
        return repositoryFactory.createPendingMaintenanceSchedulingRepository();
    }
}
