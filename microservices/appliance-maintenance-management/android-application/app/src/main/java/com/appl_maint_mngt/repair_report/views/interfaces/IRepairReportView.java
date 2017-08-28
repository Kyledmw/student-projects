package com.appl_maint_mngt.repair_report.views.interfaces;

import android.view.View;

import com.appl_maint_mngt.maintenance_schedule.models.interfaces.IMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 11/04/2017.
 */

public interface IRepairReportView {

    void update(IRepairReportReadable repairReport);

    void setDiagnosticReportOnClickListener(View.OnClickListener listener);

    void enableDiagnosticReportButton();
    void disableDiagnosticReportButton();

    void updateMaintenanceSchedule(IMaintenanceScheduleReadable maintenanceSchedule);
    void showMaintenanceSchedule();
    void hideMaintenanceSchedule();

    void updatePendingMaintenanceSchedules(List<IPendingMaintenanceScheduleReadable> list);
    void showPendingMaintenanceSchedules();
    void hidePendingMaintenanceSchedules();
}
