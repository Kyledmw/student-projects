package com.appl_maint_mngt.common.services.dummy;

import com.appl_maint_mngt.account.services.dummy.DummyUserAuthService;
import com.appl_maint_mngt.account.services.dummy.DummyUserProfileService;
import com.appl_maint_mngt.account.services.interfaces.IUserAuthService;
import com.appl_maint_mngt.account.services.interfaces.IUserProfileService;
import com.appl_maint_mngt.appliance.services.dummy.DummyApplianceService;
import com.appl_maint_mngt.appliance.services.interfaces.IApplianceService;
import com.appl_maint_mngt.appliance_status.services.dummy.DummyApplianceStatusService;
import com.appl_maint_mngt.appliance_status.services.interfaces.IApplianceStatusService;
import com.appl_maint_mngt.common.services.interfaces.IServiceFactory;
import com.appl_maint_mngt.diagnostic_report.services.dummy.DummyDiagnosticReportService;
import com.appl_maint_mngt.diagnostic_report.services.interfaces.IDiagnosticReportService;
import com.appl_maint_mngt.diagnostic_request.services.dummy.DummyDiagnosticRequestService;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;
import com.appl_maint_mngt.maintenance_engineer.services.dummy.DummyMaintenanceEngineerService;
import com.appl_maint_mngt.maintenance_engineer.services.interfaces.IMaintenanceEngineerService;
import com.appl_maint_mngt.maintenance_organisation.services.MaintenanceOrganisationService;
import com.appl_maint_mngt.maintenance_organisation.services.dummy.DummyMaintenanceOrganisationService;
import com.appl_maint_mngt.maintenance_organisation.services.interfaces.IMaintenanceOrganisationService;
import com.appl_maint_mngt.maintenance_schedule.services.dummy.DummyMaintenanceService;
import com.appl_maint_mngt.maintenance_schedule.services.interfaces.IMaintenanceScheduleService;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.dummy.DummyPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.interfaces.IPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.pending_repair_report.services.dummy.DummyPendingRepairReportService;
import com.appl_maint_mngt.pending_repair_report.services.interfaces.IPendingRepairReportService;
import com.appl_maint_mngt.property.services.IPropertyService;
import com.appl_maint_mngt.property.services.dummy.DummyPropertyService;
import com.appl_maint_mngt.property_appliance.services.dummy.DummyPropertyApplianceService;
import com.appl_maint_mngt.property_appliance.services.interfaces.IPropertyApplianceService;
import com.appl_maint_mngt.property_appliance_status_update.services.dummy.DummyPropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.property_appliance_status_update.services.interfaces.IPropertyApplianceStatusUpdateService;
import com.appl_maint_mngt.property_manager.services.dummy.DummyPropertyManangerService;
import com.appl_maint_mngt.property_manager.services.interfaces.IPropertyManagerService;
import com.appl_maint_mngt.property_tenant.services.dummy.DummyPropertyTenantService;
import com.appl_maint_mngt.property_tenant.services.interfaces.IPropertyTenantService;
import com.appl_maint_mngt.repair_report.services.dummy.DummyRepairReportService;
import com.appl_maint_mngt.repair_report.services.interfaces.IRepairReportService;

/**
 * Created by Kyle on 07/04/2017.
 */

public class DummyServiceFactory implements IServiceFactory {

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
        if(userAuthService == null) userAuthService = new DummyUserAuthService();
        return userAuthService;
    }

    @Override
    public IUserProfileService createUserProfileService() {
        if(userProfileService == null) userProfileService = new DummyUserProfileService();
        return userProfileService;
    }

    @Override
    public IMaintenanceEngineerService createMaintenanceEngineerService() {
        if(maintenanceEngineerService == null) maintenanceEngineerService = new DummyMaintenanceEngineerService();
        return maintenanceEngineerService;
    }

    @Override
    public IPropertyManagerService createPropertyManagerService() {
        if(propertyManagerService == null) propertyManagerService = new DummyPropertyManangerService();
        return propertyManagerService;
    }

    @Override
    public IPropertyTenantService createPropertyTenantService() {
        if(propertyTenantService == null) propertyTenantService = new DummyPropertyTenantService();
        return propertyTenantService;
    }

    @Override
    public IPropertyService createPropertyService() {
        if(propertyService == null) propertyService = new DummyPropertyService();
        return propertyService;
    }

    @Override
    public IPropertyApplianceService createPropertyApplianceService() {
        if(propertyApplianceService == null) propertyApplianceService = new DummyPropertyApplianceService();
        return propertyApplianceService;
    }

    @Override
    public IApplianceStatusService createApplianceStatusService() {
        if(applianceStatusService == null) applianceStatusService = new DummyApplianceStatusService();
        return applianceStatusService;
    }

    @Override
    public IApplianceService createApplianceService() {
        if(applianceService == null) applianceService = new DummyApplianceService();
        return applianceService;
    }

    @Override
    public IPropertyApplianceStatusUpdateService createPropertyApplianceStatusUpdateService() {
        if(propertyApplianceStatusUpdateService == null) propertyApplianceStatusUpdateService = new DummyPropertyApplianceStatusUpdateService();
        return propertyApplianceStatusUpdateService;
    }

    @Override
    public IDiagnosticReportService createDiagnosticReportService() {
        if(diagnosticReportService == null) diagnosticReportService = new DummyDiagnosticReportService();
        return diagnosticReportService;
    }

    @Override
    public IMaintenanceOrganisationService createMaintenanceOrganisationService() {
        if(maintenanceOrganisationService == null) maintenanceOrganisationService = new DummyMaintenanceOrganisationService();
        return maintenanceOrganisationService;
    }

    @Override
    public IDiagnosticRequestService createDiagnosticRequestService() {
        if(diagnosticRequestService == null) diagnosticRequestService = new DummyDiagnosticRequestService();
        return diagnosticRequestService;

    }

    @Override
    public IRepairReportService createRepairReportService() {
        if(repairReportService == null) repairReportService = new DummyRepairReportService();
        return repairReportService;
    }

    @Override
    public IPendingRepairReportService createPendingRepairReportService() {
        if(pendingRepairReportService == null) pendingRepairReportService = new DummyPendingRepairReportService();
        return pendingRepairReportService;
    }

    @Override
    public IMaintenanceScheduleService createMaintenanceScheduleService() {
        if(maintenanceScheduleService == null) maintenanceScheduleService = new DummyMaintenanceService();
        return maintenanceScheduleService;
    }

    @Override
    public IPendingMaintenanceSchedulingService createPendingMaintenanceSchedulingService() {
        if(pendingMaintenanceSchedulingService == null) pendingMaintenanceSchedulingService = new DummyPendingMaintenanceSchedulingService();
        return pendingMaintenanceSchedulingService;
    }
}
