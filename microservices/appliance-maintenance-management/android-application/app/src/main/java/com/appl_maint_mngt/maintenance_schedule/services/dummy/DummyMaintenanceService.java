package com.appl_maint_mngt.maintenance_schedule.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.maintenance_schedule.models.MaintenanceSchedule;
import com.appl_maint_mngt.maintenance_schedule.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance_schedule.repositories.interfaces.IMaintenanceScheduleUpdateableRepository;
import com.appl_maint_mngt.maintenance_schedule.services.interfaces.IMaintenanceScheduleService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public class DummyMaintenanceService implements IMaintenanceScheduleService {

    @Override
    public void findByRepairReportId(Long repairReportId, IErrorCallback errorCallback) {
        IMaintenanceScheduleUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getMaintenanceScheduleRepository();
        MaintenanceSchedule maintSched = new MaintenanceSchedule();
        maintSched.setId((long) 1);
        maintSched.setStartTime(new Timestamp(System.currentTimeMillis()));
        maintSched.setEndTime(new Timestamp(System.currentTimeMillis()));
        maintSched.setRepairReportId(repairReportId);
        maintSched.setScheduleStatus(ScheduleStatus.NORMAL);
        repository.addItem(maintSched);

    }

    @Override
    public void findByRepairReportIdIn(List<Long> repairReportIds, IErrorCallback errorCallback) {
        IMaintenanceScheduleUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getMaintenanceScheduleRepository();
        List<MaintenanceSchedule> list = new ArrayList<>();
        long count = 1;
        for(Long repairReportId: repairReportIds) {
            MaintenanceSchedule maintSched = new MaintenanceSchedule();
            maintSched.setScheduleStatus(ScheduleStatus.NORMAL);
            maintSched.setRepairReportId(repairReportId);
            maintSched.setEndTime(new Timestamp(System.currentTimeMillis()));
            maintSched.setStartTime(new Timestamp(System.currentTimeMillis()));
            maintSched.setId(count);
            count++;
            list.add(maintSched);
        }
        repository.addItems(list);
    }
}
