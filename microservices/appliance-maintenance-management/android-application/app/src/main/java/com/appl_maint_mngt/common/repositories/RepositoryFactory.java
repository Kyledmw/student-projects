package com.appl_maint_mngt.common.repositories;

import com.appl_maint_mngt.account.repositories.AAccountRepository;
import com.appl_maint_mngt.account.repositories.AccountRepository;
import com.appl_maint_mngt.appliance.repositories.AApplianceRepository;
import com.appl_maint_mngt.appliance.repositories.ApplianceRepository;
import com.appl_maint_mngt.appliance_status.repositories.AApplianceStatusRepository;
import com.appl_maint_mngt.appliance_status.repositories.ApplianceStatusRepository;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryFactory;
import com.appl_maint_mngt.diagnostic_report.repositories.ADiagnosticReportRepository;
import com.appl_maint_mngt.diagnostic_report.repositories.DiagnosticReportRepository;
import com.appl_maint_mngt.diagnostic_request.repositories.ADiagnosticRequestRepository;
import com.appl_maint_mngt.diagnostic_request.repositories.DiagnosticRequestRepository;
import com.appl_maint_mngt.maintenance_engineer.repositories.AMaintenanceEngineerRepository;
import com.appl_maint_mngt.maintenance_engineer.repositories.MaintenanceEngineerRepository;
import com.appl_maint_mngt.maintenance_organisation.repositories.AMaintenanceOrganisationRepository;
import com.appl_maint_mngt.maintenance_organisation.repositories.MaintenanceOrganisationRepository;
import com.appl_maint_mngt.maintenance_schedule.repositories.AMaintenanceScheduleRepository;
import com.appl_maint_mngt.maintenance_schedule.repositories.MaintenanceScheduleRepository;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.APendingMaintenanceSchedulingRepository;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.PendingMaintenanceSchedulingRepository;
import com.appl_maint_mngt.pending_repair_report.repositories.APendingRepairReportRepository;
import com.appl_maint_mngt.pending_repair_report.repositories.PendingRepairReportRepository;
import com.appl_maint_mngt.property.repositories.APropertyRepository;
import com.appl_maint_mngt.property.repositories.PropertyRepository;
import com.appl_maint_mngt.property_appliance.repositories.APropertyApplianceRepository;
import com.appl_maint_mngt.property_appliance.repositories.PropertyApplianceRepository;
import com.appl_maint_mngt.property_manager.models.APropertyManager;
import com.appl_maint_mngt.property_manager.repositories.APropertyManagerRepository;
import com.appl_maint_mngt.property_manager.repositories.PropertyManagerRepository;
import com.appl_maint_mngt.property_tenant.repositories.APropertyTenantRepository;
import com.appl_maint_mngt.property_tenant.repositories.PropertyTenantRepository;
import com.appl_maint_mngt.repair_report.repositories.ARepairReportRepository;
import com.appl_maint_mngt.repair_report.repositories.RepairReportRepository;

/**
 * Created by Kyle on 07/04/2017.
 */

public class RepositoryFactory implements IRepositoryFactory {

    private AAccountRepository accountRepository;
    private AMaintenanceEngineerRepository maintenanceEngineerRepository;
    private APropertyManagerRepository propertyManagerRepository;
    private APropertyTenantRepository propertyTenantRepository;
    private APropertyRepository propertyRepository;
    private APropertyApplianceRepository propertyApplianceRepository;
    private AApplianceStatusRepository applianceStatusRepository;
    private AApplianceRepository applianceRepository;
    private ADiagnosticReportRepository diagnosticReportRepository;
    private AMaintenanceOrganisationRepository maintenanceOrganisationRepository;
    private ADiagnosticRequestRepository diagnosticRequestRepository;
    private ARepairReportRepository repairReportRepository;
    private APendingRepairReportRepository pendingRepairReportRepository;
    private AMaintenanceScheduleRepository maintenanceScheduleRepository;
    private APendingMaintenanceSchedulingRepository pendingMaintenanceSchedulingRepository;

    @Override
    public void clear() {
        accountRepository = null;

    }

    @Override
    public AAccountRepository createAccountRepository() {
        if(accountRepository == null) accountRepository = new AccountRepository();
        return accountRepository;
    }

    @Override
    public AMaintenanceEngineerRepository createMaintenanceEngineerRepository() {
        if(maintenanceEngineerRepository == null) maintenanceEngineerRepository = new MaintenanceEngineerRepository();
        return maintenanceEngineerRepository;
    }

    @Override
    public APropertyManagerRepository createPropertyManagerRepository() {
        if(propertyManagerRepository == null) propertyManagerRepository = new PropertyManagerRepository();
        return propertyManagerRepository;
    }

    @Override
    public APropertyTenantRepository createPropertyTenantRepository() {
        if(propertyTenantRepository == null) propertyTenantRepository = new PropertyTenantRepository();
        return propertyTenantRepository;
    }

    @Override
    public APropertyRepository createPropertyRepository() {
        if(propertyRepository == null) propertyRepository = new PropertyRepository();
        return propertyRepository;
    }

    @Override
    public APropertyApplianceRepository createPropertyApplianceRepository() {
        if(propertyApplianceRepository == null) propertyApplianceRepository = new PropertyApplianceRepository();
        return propertyApplianceRepository;
    }

    @Override
    public AApplianceStatusRepository createApplianceStatusRepository() {
        if(applianceStatusRepository == null) applianceStatusRepository = new ApplianceStatusRepository();
        return applianceStatusRepository;
    }

    @Override
    public AApplianceRepository createApplianceRepository() {
        if(applianceRepository == null) applianceRepository = new ApplianceRepository();
        return applianceRepository;
    }

    @Override
    public ADiagnosticReportRepository createDiagnosticReportRepository() {
        if(diagnosticReportRepository == null) diagnosticReportRepository = new DiagnosticReportRepository();
        return diagnosticReportRepository;
    }

    @Override
    public AMaintenanceOrganisationRepository createMaintenanceOrganisationRepository() {
        if(maintenanceOrganisationRepository == null) maintenanceOrganisationRepository = new MaintenanceOrganisationRepository();
        return maintenanceOrganisationRepository;
    }

    @Override
    public ADiagnosticRequestRepository createDiagnosticRequestRepository() {
        if(diagnosticRequestRepository == null) diagnosticRequestRepository = new DiagnosticRequestRepository();
        return diagnosticRequestRepository;
    }

    @Override
    public ARepairReportRepository createRepairReportRepository() {
        if(repairReportRepository == null) repairReportRepository = new RepairReportRepository();
        return repairReportRepository;
    }

    @Override
    public APendingRepairReportRepository createPendingRepairReportRepository() {
        if(pendingRepairReportRepository == null) pendingRepairReportRepository = new PendingRepairReportRepository();
        return pendingRepairReportRepository;
    }

    @Override
    public AMaintenanceScheduleRepository createMaintenanceScheduleRepository() {
        if(maintenanceScheduleRepository == null) maintenanceScheduleRepository = new MaintenanceScheduleRepository();
        return maintenanceScheduleRepository;
    }

    @Override
    public APendingMaintenanceSchedulingRepository createPendingMaintenanceSchedulingRepository() {
        if(pendingMaintenanceSchedulingRepository == null) pendingMaintenanceSchedulingRepository = new PendingMaintenanceSchedulingRepository();
        return pendingMaintenanceSchedulingRepository;
    }
}
