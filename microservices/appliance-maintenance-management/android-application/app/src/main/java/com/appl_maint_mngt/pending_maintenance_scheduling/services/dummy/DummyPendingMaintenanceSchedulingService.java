package com.appl_maint_mngt.pending_maintenance_scheduling.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.ScheduleStatus;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.webs.interfaces.IPendingMaintenanceSchedulePayload;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.interfaces.IPendingMaintenanceSchedulingUpdateableRepository;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.interfaces.IPendingMaintenanceSchedulingService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 15/04/2017.
 */

public class DummyPendingMaintenanceSchedulingService implements IPendingMaintenanceSchedulingService {


    @Override
    public void add(IPendingMaintenanceSchedulePayload payload, IErrorCallback errorCallback) {
        IPendingMaintenanceSchedulingUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingMaintenanceSchedulingRepository();

        PendingMaintenanceSchedule schedule = new PendingMaintenanceSchedule();
        schedule.setEndTime(payload.getEndTime());
        schedule.setId((long) 1);
        schedule.setRepairReportId(payload.getReportId());
        schedule.setSchedulerType(payload.getSchedulerType());
        schedule.setScheduleStatus(ScheduleStatus.PENDING);
        schedule.setStartTime(payload.getStartTime());

        repository.addItem(schedule);
    }

    @Override
    public void getPendingSchedules(Long reportId, SchedulerType type, IErrorCallback errorCallback) {
        IPendingMaintenanceSchedulingUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingMaintenanceSchedulingRepository();
        List<PendingMaintenanceSchedule> list = new ArrayList<>();

        PendingMaintenanceSchedule schedule = new PendingMaintenanceSchedule();
        schedule.setEndTime(new Timestamp(System.currentTimeMillis()));
        schedule.setId((long) 1);
        schedule.setRepairReportId(reportId);
        schedule.setSchedulerType(type);
        schedule.setScheduleStatus(ScheduleStatus.PENDING);
        schedule.setStartTime(new Timestamp(System.currentTimeMillis()));
        list.add(schedule);

        repository.addItems(list);
    }

    @Override
    public void getAllPending(Long reportId, IErrorCallback errorCallback) {
        IPendingMaintenanceSchedulingUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingMaintenanceSchedulingRepository();
        List<PendingMaintenanceSchedule> list = new ArrayList<>();

        PendingMaintenanceSchedule schedule = new PendingMaintenanceSchedule();
        schedule.setEndTime(new Timestamp(System.currentTimeMillis()));
        schedule.setId((long) 1);
        schedule.setRepairReportId(reportId);
        schedule.setSchedulerType(SchedulerType.PROPERTY_REPRESENTITIVE);
        schedule.setScheduleStatus(ScheduleStatus.PENDING);
        schedule.setStartTime(new Timestamp(System.currentTimeMillis()));
        list.add(schedule);

        repository.addItems(list);
    }

    @Override
    public void accept(Long id, IErrorCallback errorCallback) {
        IPendingMaintenanceSchedulingUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingMaintenanceSchedulingRepository();

        PendingMaintenanceSchedule schedule = new PendingMaintenanceSchedule();
        schedule.setEndTime(new Timestamp(System.currentTimeMillis()));
        schedule.setId(id);
        schedule.setRepairReportId((long) 1);
        schedule.setSchedulerType(SchedulerType.PROPERTY_REPRESENTITIVE);
        schedule.setScheduleStatus(ScheduleStatus.ACCEPTED);
        schedule.setStartTime(new Timestamp(System.currentTimeMillis()));

        repository.addItem(schedule);
    }

    @Override
    public void decline(Long id, IErrorCallback errorCallback) {
        IPendingMaintenanceSchedulingUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingMaintenanceSchedulingRepository();

        PendingMaintenanceSchedule schedule = new PendingMaintenanceSchedule();
        schedule.setEndTime(new Timestamp(System.currentTimeMillis()));
        schedule.setId(id);
        schedule.setRepairReportId((long) 1);
        schedule.setSchedulerType(SchedulerType.PROPERTY_REPRESENTITIVE);
        schedule.setScheduleStatus(ScheduleStatus.DECLINED);
        schedule.setStartTime(new Timestamp(System.currentTimeMillis()));

        repository.addItem(schedule);
    }
}
