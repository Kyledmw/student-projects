package com.appl_maint_mngt.common.controllers.interfaces;

import com.appl_maint_mngt.account.controllers.interfaces.IAccountController;
import com.appl_maint_mngt.appliance.controllers.interfaces.IApplianceController;
import com.appl_maint_mngt.appliance_status.controllers.interfaces.IApplianceStatusController;
import com.appl_maint_mngt.diagnostic_report.controllers.interfaces.IDiagnosticReportController;
import com.appl_maint_mngt.diagnostic_request.controllers.interfaces.IDiagnosticRequestController;
import com.appl_maint_mngt.maintenance_engineer.controllers.interfaces.IMaintenanceEngineerController;
import com.appl_maint_mngt.maintenance_organisation.controllers.interfaces.IMaintenanceOrganisationController;
import com.appl_maint_mngt.maintenance_schedule.controllers.interfaces.IMaintenanceScheduleController;
import com.appl_maint_mngt.pending_maintenance_scheduling.controllers.interfaces.IPendingMaintenanceSchedulingController;
import com.appl_maint_mngt.pending_repair_report.controllers.interfaces.IPendingRepairReportController;
import com.appl_maint_mngt.property.controller.interfaces.IPropertyController;
import com.appl_maint_mngt.property_appliance.controllers.interfaces.IPropertyApplianceController;
import com.appl_maint_mngt.property_appliance_status_update.controllers.interfaces.IPropertyApplianceStatusUpdateController;
import com.appl_maint_mngt.property_manager.controllers.interfaces.IPropertyManagerController;
import com.appl_maint_mngt.property_tenant.controllers.interfaces.IPropertyTenantController;
import com.appl_maint_mngt.repair_report.controllers.interfaces.IRepairReportController;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IControllerFactory {

    IAccountController createAccountController();
    IMaintenanceEngineerController createMaintenanceEngineerController();
    IPropertyManagerController createPropertyManangerController();
    IPropertyTenantController createPropertyTenantController();
    IPropertyController createPropertyController();
    IPropertyApplianceController createPropertyApplianceController();
    IApplianceStatusController createApplianceStatusController();
    IApplianceController createApplianceController();
    IPropertyApplianceStatusUpdateController createPropertyApplianceStatusUpdateController();
    IDiagnosticReportController createDiagnosticReportController();
    IMaintenanceOrganisationController createMaintenanceOrganisationController();
    IDiagnosticRequestController createDiagnosticRequestController();
    IRepairReportController createRepairReportController();
    IPendingRepairReportController createPendingRepairReportController();
    IMaintenanceScheduleController createMaintenanceScheduleController();
    IPendingMaintenanceSchedulingController createPendingMaintenanceSchedulingController();
}
