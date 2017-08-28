package com.appl_maint_mngt.common.services.interfaces;

import com.appl_maint_mngt.account.services.interfaces.IUserAuthService;
import com.appl_maint_mngt.account.services.interfaces.IUserProfileService;
import com.appl_maint_mngt.appliance.services.interfaces.IApplianceService;
import com.appl_maint_mngt.appliance_status.services.interfaces.IApplianceStatusService;
import com.appl_maint_mngt.diagnostic_report.services.interfaces.IDiagnosticReportService;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;
import com.appl_maint_mngt.maintenance_engineer.services.interfaces.IMaintenanceEngineerService;
import com.appl_maint_mngt.maintenance_organisation.services.interfaces.IMaintenanceOrganisationService;
import com.appl_maint_mngt.maintenance_schedule.services.interfaces.IMaintenanceScheduleService;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.interfaces.IPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.pending_repair_report.services.interfaces.IPendingRepairReportService;
import com.appl_maint_mngt.property.services.IPropertyService;
import com.appl_maint_mngt.property_appliance.services.interfaces.IPropertyApplianceService;
import com.appl_maint_mngt.property_appliance_status_update.services.interfaces.IPropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.property_manager.services.interfaces.IPropertyManagerService;
import com.appl_maint_mngt.property_tenant.services.interfaces.IPropertyTenantService;
import com.appl_maint_mngt.repair_report.services.interfaces.IRepairReportService;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IServiceFactory {

    IUserAuthService createUserAuthService();
    IUserProfileService createUserProfileService();
    IMaintenanceEngineerService createMaintenanceEngineerService();
    IPropertyManagerService createPropertyManagerService();
    IPropertyTenantService createPropertyTenantService();
    IPropertyService createPropertyService();
    IPropertyApplianceService createPropertyApplianceService();
    IApplianceStatusService createApplianceStatusService();
    IApplianceService createApplianceService();
    IPropertyApplianceStatusUpdateService createPropertyApplianceStatusUpdateService();
    IDiagnosticReportService createDiagnosticReportService();
    IMaintenanceOrganisationService createMaintenanceOrganisationService();
    IDiagnosticRequestService createDiagnosticRequestService();
    IRepairReportService createRepairReportService();
    IPendingRepairReportService createPendingRepairReportService();
    IMaintenanceScheduleService createMaintenanceScheduleService();
    IPendingMaintenanceSchedulingService createPendingMaintenanceSchedulingService();
}
