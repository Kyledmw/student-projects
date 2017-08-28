package com.appl_maint_mngt.common.controllers;

import com.appl_maint_mngt.account.controllers.AccountController;
import com.appl_maint_mngt.account.controllers.interfaces.IAccountController;
import com.appl_maint_mngt.appliance.controllers.ApplianceController;
import com.appl_maint_mngt.appliance.controllers.interfaces.IApplianceController;
import com.appl_maint_mngt.appliance_status.controllers.ApplianceStatusController;
import com.appl_maint_mngt.appliance_status.controllers.interfaces.IApplianceStatusController;
import com.appl_maint_mngt.common.controllers.interfaces.IControllerFactory;
import com.appl_maint_mngt.diagnostic_report.controllers.DiagnosticReportController;
import com.appl_maint_mngt.diagnostic_report.controllers.interfaces.IDiagnosticReportController;
import com.appl_maint_mngt.diagnostic_request.controllers.DiagnosticRequestController;
import com.appl_maint_mngt.diagnostic_request.controllers.interfaces.IDiagnosticRequestController;
import com.appl_maint_mngt.maintenance_engineer.controllers.MaintenanceEngineerController;
import com.appl_maint_mngt.maintenance_engineer.controllers.interfaces.IMaintenanceEngineerController;
import com.appl_maint_mngt.maintenance_organisation.controllers.MaintenanceOrganisationController;
import com.appl_maint_mngt.maintenance_organisation.controllers.interfaces.IMaintenanceOrganisationController;
import com.appl_maint_mngt.maintenance_schedule.controllers.MaintenanceScheduleController;
import com.appl_maint_mngt.maintenance_schedule.controllers.interfaces.IMaintenanceScheduleController;
import com.appl_maint_mngt.pending_maintenance_scheduling.controllers.PendingMaintenanceSchedulingController;
import com.appl_maint_mngt.pending_maintenance_scheduling.controllers.interfaces.IPendingMaintenanceSchedulingController;
import com.appl_maint_mngt.pending_repair_report.controllers.PendingRepairReportController;
import com.appl_maint_mngt.pending_repair_report.controllers.interfaces.IPendingRepairReportController;
import com.appl_maint_mngt.property.controller.PropertyController;
import com.appl_maint_mngt.property.controller.interfaces.IPropertyController;
import com.appl_maint_mngt.property_appliance.controllers.PropertyApplianceController;
import com.appl_maint_mngt.property_appliance.controllers.interfaces.IPropertyApplianceController;
import com.appl_maint_mngt.property_appliance_status_update.controllers.PropertyApplianceStatusUpdateController;
import com.appl_maint_mngt.property_appliance_status_update.controllers.interfaces.IPropertyApplianceStatusUpdateController;
import com.appl_maint_mngt.property_manager.controllers.PropertyManagerController;
import com.appl_maint_mngt.property_manager.controllers.interfaces.IPropertyManagerController;
import com.appl_maint_mngt.property_tenant.controllers.PropertyTenantController;
import com.appl_maint_mngt.property_tenant.controllers.interfaces.IPropertyTenantController;
import com.appl_maint_mngt.repair_report.controllers.RepairReportController;
import com.appl_maint_mngt.repair_report.controllers.interfaces.IRepairReportController;

/**
 * Created by Kyle on 07/04/2017.
 */

public class ControllerFactory implements IControllerFactory {

    private IAccountController accountController;
    private IMaintenanceEngineerController maintenanceEngineerController;
    private IPropertyManagerController propertyManagerController;
    private IPropertyTenantController propertyTenantController;
    private IPropertyController propertyController;
    private IPropertyApplianceController propertyApplianceController;
    private IApplianceStatusController applianceStatusController;
    private IApplianceController applianceController;
    private IPropertyApplianceStatusUpdateController propertyApplianceStatusUpdateController;
    private IDiagnosticReportController diagnosticReportController;
    private IMaintenanceOrganisationController maintenanceOrganisationController;
    private IDiagnosticRequestController diagnosticRequestController;
    private IRepairReportController repairReportController;
    private IPendingRepairReportController pendingRepairReportController;
    private IMaintenanceScheduleController maintenanceScheduleController;
    private IPendingMaintenanceSchedulingController pendingMaintenanceSchedulingController;

    @Override
    public IAccountController createAccountController() {
        if(accountController == null) accountController = new AccountController();
        return accountController;
    }

    @Override
    public IMaintenanceEngineerController createMaintenanceEngineerController() {
        if(maintenanceEngineerController == null) maintenanceEngineerController = new MaintenanceEngineerController();
        return maintenanceEngineerController;
    }

    @Override
    public IPropertyManagerController createPropertyManangerController() {
        if(propertyManagerController == null) propertyManagerController = new PropertyManagerController();
        return propertyManagerController;
    }

    @Override
    public IPropertyTenantController createPropertyTenantController() {
        if(propertyTenantController == null) propertyTenantController = new PropertyTenantController();
        return propertyTenantController;
    }

    @Override
    public IPropertyController createPropertyController() {
        if(propertyController == null) propertyController = new PropertyController();
        return propertyController;
    }

    @Override
    public IPropertyApplianceController createPropertyApplianceController() {
        if(propertyApplianceController == null) propertyApplianceController = new PropertyApplianceController();
        return propertyApplianceController;
    }

    @Override
    public IApplianceStatusController createApplianceStatusController() {
        if(applianceStatusController == null) applianceStatusController = new ApplianceStatusController();
        return applianceStatusController;
    }

    @Override
    public IApplianceController createApplianceController() {
        if(applianceController == null) applianceController = new ApplianceController();
        return applianceController;
    }

    @Override
    public IPropertyApplianceStatusUpdateController createPropertyApplianceStatusUpdateController() {
        if(propertyApplianceStatusUpdateController == null) propertyApplianceStatusUpdateController = new PropertyApplianceStatusUpdateController();
        return propertyApplianceStatusUpdateController;
    }

    @Override
    public IDiagnosticReportController createDiagnosticReportController() {
        if(diagnosticReportController == null) diagnosticReportController = new DiagnosticReportController();
        return diagnosticReportController;
    }

    @Override
    public IMaintenanceOrganisationController createMaintenanceOrganisationController() {
        if(maintenanceOrganisationController == null) maintenanceOrganisationController = new MaintenanceOrganisationController();
        return maintenanceOrganisationController;
    }

    @Override
    public IDiagnosticRequestController createDiagnosticRequestController() {
        if(diagnosticRequestController == null) diagnosticRequestController = new DiagnosticRequestController();
        return diagnosticRequestController;
    }

    @Override
    public IRepairReportController createRepairReportController() {
        if(repairReportController == null) repairReportController = new RepairReportController();
        return repairReportController;
    }

    @Override
    public IPendingRepairReportController createPendingRepairReportController() {
        if(pendingRepairReportController == null) pendingRepairReportController = new PendingRepairReportController();
        return pendingRepairReportController;
    }

    @Override
    public IMaintenanceScheduleController createMaintenanceScheduleController() {
        if(maintenanceScheduleController == null) maintenanceScheduleController = new MaintenanceScheduleController();
        return maintenanceScheduleController;
    }

    @Override
    public IPendingMaintenanceSchedulingController createPendingMaintenanceSchedulingController() {
        if(pendingMaintenanceSchedulingController == null) pendingMaintenanceSchedulingController = new PendingMaintenanceSchedulingController();
        return pendingMaintenanceSchedulingController;
    }
}
