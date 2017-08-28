package com.appl_maint_mngt.common.repositories.interfaces;

import com.appl_maint_mngt.account.repositories.interfaces.IAccountReadableRepository;
import com.appl_maint_mngt.appliance.repositories.interfaces.IApplianceReadableRepository;
import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusReadableRepository;
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

public interface IReadableRepositoryRetriever {

    IAccountReadableRepository getAccountRepository();
    IMaintenanceEngineerReadableRepository getMaintenanceEngineerRepository();
    IPropertyManagerReadableRepository getPropertyManangerRepository();
    IPropertyTenantReadableRepository getPropertyTenantRepository();
    IPropertyReadableRepository getPropertyRepository();
    IPropertyApplianceReadableRepository getPropertyApplianceRepository();
    IApplianceStatusReadableRepository getApplianceStatusRepository();
    IApplianceReadableRepository getApplianceRepository();
    IDiagnosticReportReadableRepository getDiagnosticReportRepository();
    IMaintenanceOrganisationReadableRepository getMaintenanceOrganisationRepository();
    IDiagnosticRequestReadableRepository getDiagnosticRequestRepository();
    IRepairReportReadableRepository getRepairReportReadableRepository();
    IPendingRepairReportReadableRepository getPendingRepairReportReadableRepository();
    IMaintenanceScheduleReadableRepository getMaintenanceScheduleReadableRepository();
    IPendingMaintenanceSchedulingReadableRepository getPendingMaintenanceSchedulingReadableRepository();
}
