package com.appl_maint_mngt.common.repositories.interfaces;

import com.appl_maint_mngt.account.repositories.interfaces.IAccountUpdateableRepository;
import com.appl_maint_mngt.appliance.repositories.interfaces.IApplianceUpdateableRepository;
import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusUpdateableRepository;
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

public interface IUpdateableRepositoryRetriever {

    IAccountUpdateableRepository getAccountRepository();
    IMaintenanceEngineerUpdateableRepository getMaintenanceEngineerRepository();
    IPropertyManagerUpdateableRepository getPropertyManagerRepository();
    IPropertyTenantUpdateableRepository getPropertyTenantRepository();
    IPropertyUpdateableRepository getPropertyRepository();
    IPropertyApplianceUpdateableRepository getPropertyApplianceRepository();
    IApplianceStatusUpdateableRepository getApplianceStatusRepository();
    IApplianceUpdateableRepository getApplianceRepository();
    IDiagnosticReportUpdateableRepository getDiagnosticReportRepository();
    IMaintenanceOrganisationUpdateableRepository getMaintenanceOrganisationRepository();
    IDiagnosticRequestUpdateableRepository getDiagnosticRequestRepository();
    IRepairReportUpdateableRepository getRepairReportRepository();
    IPendingRepairReportUpdateableRepository getPendingRepairReportRepository();
    IMaintenanceScheduleUpdateableRepository getMaintenanceScheduleRepository();
    IPendingMaintenanceSchedulingUpdateableRepository getPendingMaintenanceSchedulingRepository();
}
