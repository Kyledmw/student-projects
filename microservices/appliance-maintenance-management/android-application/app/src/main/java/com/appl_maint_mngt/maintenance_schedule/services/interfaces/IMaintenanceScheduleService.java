package com.appl_maint_mngt.maintenance_schedule.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public interface IMaintenanceScheduleService {
    void findByRepairReportId(Long repairReportId, IErrorCallback errorCallback);
    void findByRepairReportIdIn(List<Long> repairReportId, IErrorCallback errorCallback);
}
