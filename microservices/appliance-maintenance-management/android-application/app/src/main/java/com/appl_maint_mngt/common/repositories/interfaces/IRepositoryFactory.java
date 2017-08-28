package com.appl_maint_mngt.common.repositories.interfaces;

import com.appl_maint_mngt.account.repositories.AAccountRepository;
import com.appl_maint_mngt.appliance.repositories.AApplianceRepository;
import com.appl_maint_mngt.appliance_status.repositories.AApplianceStatusRepository;
import com.appl_maint_mngt.diagnostic_report.repositories.ADiagnosticReportRepository;
import com.appl_maint_mngt.diagnostic_request.repositories.ADiagnosticRequestRepository;
import com.appl_maint_mngt.maintenance_engineer.repositories.AMaintenanceEngineerRepository;
import com.appl_maint_mngt.maintenance_organisation.repositories.AMaintenanceOrganisationRepository;
import com.appl_maint_mngt.maintenance_schedule.repositories.AMaintenanceScheduleRepository;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.APendingMaintenanceSchedulingRepository;
import com.appl_maint_mngt.pending_repair_report.repositories.APendingRepairReportRepository;
import com.appl_maint_mngt.property.repositories.APropertyRepository;
import com.appl_maint_mngt.property_appliance.repositories.APropertyApplianceRepository;
import com.appl_maint_mngt.property_manager.repositories.APropertyManagerRepository;
import com.appl_maint_mngt.property_tenant.repositories.APropertyTenantRepository;
import com.appl_maint_mngt.repair_report.repositories.ARepairReportRepository;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IRepositoryFactory {
    void clear();

    AAccountRepository createAccountRepository();
    AMaintenanceEngineerRepository createMaintenanceEngineerRepository();
    APropertyManagerRepository createPropertyManagerRepository();
    APropertyTenantRepository createPropertyTenantRepository();
    APropertyRepository createPropertyRepository();
    APropertyApplianceRepository createPropertyApplianceRepository();
    AApplianceStatusRepository createApplianceStatusRepository();
    AApplianceRepository createApplianceRepository();
    ADiagnosticReportRepository createDiagnosticReportRepository();
    AMaintenanceOrganisationRepository createMaintenanceOrganisationRepository();
    ADiagnosticRequestRepository createDiagnosticRequestRepository();
    ARepairReportRepository createRepairReportRepository();
    APendingRepairReportRepository createPendingRepairReportRepository();
    AMaintenanceScheduleRepository createMaintenanceScheduleRepository();
    APendingMaintenanceSchedulingRepository createPendingMaintenanceSchedulingRepository();
}
