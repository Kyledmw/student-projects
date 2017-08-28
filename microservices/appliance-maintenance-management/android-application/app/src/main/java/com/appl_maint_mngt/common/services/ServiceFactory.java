package com.appl_maint_mngt.common.services;

import com.appl_maint_mngt.account.services.UserAuthService;
import com.appl_maint_mngt.account.services.UserProfileService;
import com.appl_maint_mngt.account.services.interfaces.IUserAuthService;
import com.appl_maint_mngt.account.services.interfaces.IUserProfileService;
import com.appl_maint_mngt.appliance.services.ApplianceService;
import com.appl_maint_mngt.appliance.services.interfaces.IApplianceService;
import com.appl_maint_mngt.appliance_status.services.ApplianceStatusService;
import com.appl_maint_mngt.appliance_status.services.interfaces.IApplianceStatusService;
import com.appl_maint_mngt.common.services.interfaces.IServiceFactory;
import com.appl_maint_mngt.diagnostic_report.services.DiagnosticReportService;
import com.appl_maint_mngt.diagnostic_report.services.interfaces.IDiagnosticReportService;
import com.appl_maint_mngt.diagnostic_request.services.DiagnosticRequestService;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;
import com.appl_maint_mngt.maintenance_engineer.services.MaintenanceEngineerService;
import com.appl_maint_mngt.maintenance_engineer.services.interfaces.IMaintenanceEngineerService;
import com.appl_maint_mngt.maintenance_organisation.services.MaintenanceOrganisationService;
import com.appl_maint_mngt.maintenance_organisation.services.interfaces.IMaintenanceOrganisationService;
import com.appl_maint_mngt.maintenance_schedule.services.MaintenanceScheduleService;
import com.appl_maint_mngt.maintenance_schedule.services.interfaces.IMaintenanceScheduleService;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.PendingMaintenanceSchedulingService;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.interfaces.IPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.pending_repair_report.services.PendingRepairReportService;
import com.appl_maint_mngt.pending_repair_report.services.interfaces.IPendingRepairReportService;
import com.appl_maint_mngt.property.services.IPropertyService;
import com.appl_maint_mngt.property.services.PropertyService;
import com.appl_maint_mngt.property_appliance.services.PropertyApplianceService;
import com.appl_maint_mngt.property_appliance.services.interfaces.IPropertyApplianceService;
import com.appl_maint_mngt.property_appliance_status_update.services.PropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.property_appliance_status_update.services.interfaces.IPropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.property_manager.services.PropertyManagerService;
import com.appl_maint_mngt.property_manager.services.interfaces.IPropertyManagerService;
import com.appl_maint_mngt.property_tenant.services.PropertyTenantService;
import com.appl_maint_mngt.property_tenant.services.interfaces.IPropertyTenantService;
import com.appl_maint_mngt.repair_report.services.RepairReportService;
import com.appl_maint_mngt.repair_report.services.interfaces.IRepairReportService;

/**
 * Created by Kyle on 07/04/2017.
 */

public class ServiceFactory implements IServiceFactory {

    private IUserAuthService userAuthService;
    private IUserProfileService userProfileService;
    private IMaintenanceEngineerService maintenanceEngineerService;
    private IPropertyManagerService propertyManagerService;
    private IPropertyTenantService propertyTenantService;
    private IPropertyService propertyService;
    private IPropertyApplianceService propertyApplianceService;
    private IApplianceStatusService applianceStatusService;
    private IApplianceService applianceService;
    private IPropertyApplianceStatusUpdateService propertyApplianceStatusUpdateService;
    private IDiagnosticReportService diagnosticReportService;
    private IMaintenanceOrganisationService maintenanceOrganisationService;
    private IDiagnosticRequestService diagnosticRequestService;
    private IRepairReportService repairReportService;
    private IPendingRepairReportService pendingRepairReportService;
    private IMaintenanceScheduleService maintenanceScheduleService;
    private IPendingMaintenanceSchedulingService pendingMaintenanceSchedulingService;

    @Override
    public IUserAuthService createUserAuthService() {
        if(userAuthService == null) userAuthService = new UserAuthService();
        return userAuthService;
    }

    @Override
    public IUserProfileService createUserProfileService() {
        if(userProfileService == null) userProfileService = new UserProfileService();
        return userProfileService;
    }

    @Override
    public IMaintenanceEngineerService createMaintenanceEngineerService() {
        if(maintenanceEngineerService == null) maintenanceEngineerService = new MaintenanceEngineerService();
        return maintenanceEngineerService;
    }

    @Override
    public IPropertyManagerService createPropertyManagerService() {
        if(propertyManagerService == null) propertyManagerService = new PropertyManagerService();
        return propertyManagerService;
    }

    @Override
    public IPropertyTenantService createPropertyTenantService() {
        if(propertyTenantService == null) propertyTenantService = new PropertyTenantService();
        return propertyTenantService;
    }

    @Override
    public IPropertyService createPropertyService() {
        if(propertyService == null) propertyService = new PropertyService();
        return propertyService;
    }

    @Override
    public IPropertyApplianceService createPropertyApplianceService() {
        if(propertyApplianceService == null) propertyApplianceService = new PropertyApplianceService();
        return propertyApplianceService;
    }

    @Override
    public IApplianceStatusService createApplianceStatusService() {
        if(applianceStatusService == null) applianceStatusService = new ApplianceStatusService();
        return applianceStatusService;
    }

    @Override
    public IApplianceService createApplianceService() {
        if(applianceService == null) applianceService = new ApplianceService();
        return applianceService;
    }

    @Override
    public IPropertyApplianceStatusUpdateService createPropertyApplianceStatusUpdateService() {
        if(propertyApplianceStatusUpdateService == null) propertyApplianceStatusUpdateService = new PropertyApplianceStatusUpdateService();
        return propertyApplianceStatusUpdateService;
    }

    @Override
    public IDiagnosticReportService createDiagnosticReportService() {
        if(diagnosticReportService == null) diagnosticReportService = new DiagnosticReportService();
        return diagnosticReportService;
    }

    @Override
    public IMaintenanceOrganisationService createMaintenanceOrganisationService() {
        if(maintenanceOrganisationService == null) maintenanceOrganisationService = new MaintenanceOrganisationService();
        return maintenanceOrganisationService;
    }

    @Override
    public IDiagnosticRequestService createDiagnosticRequestService() {
        if(diagnosticRequestService == null) diagnosticRequestService = new DiagnosticRequestService();
        return diagnosticRequestService;
    }

    @Override
    public IRepairReportService createRepairReportService() {
        if(repairReportService == null) repairReportService = new RepairReportService();
        return repairReportService;
    }

    @Override
    public IPendingRepairReportService createPendingRepairReportService() {
        if(pendingRepairReportService == null) pendingRepairReportService = new PendingRepairReportService();
        return pendingRepairReportService;
    }

    @Override
    public IMaintenanceScheduleService createMaintenanceScheduleService() {
        if(maintenanceScheduleService == null) maintenanceScheduleService = new MaintenanceScheduleService();
        return maintenanceScheduleService;
    }

    @Override
    public IPendingMaintenanceSchedulingService createPendingMaintenanceSchedulingService() {
        if(pendingMaintenanceSchedulingService == null) pendingMaintenanceSchedulingService = new PendingMaintenanceSchedulingService();
        return pendingMaintenanceSchedulingService;
    }
}
