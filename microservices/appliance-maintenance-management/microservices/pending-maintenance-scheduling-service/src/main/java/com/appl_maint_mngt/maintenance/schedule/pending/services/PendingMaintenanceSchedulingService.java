package com.appl_maint_mngt.maintenance.schedule.pending.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mngt.maintenance.schedule.pending.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.ScheduleStatus;
import com.appl_maint_mngt.maintenance.schedule.pending.models.constants.SchedulerType;
import com.appl_maint_mngt.maintenance.schedule.pending.repositories.IPendingMaintenanceSchedulingRepository;

@Service
public class PendingMaintenanceSchedulingService implements IPendingMaintenanceSchedulingService {
	
	@Autowired
	private IPendingMaintenanceSchedulingRepository repo;

	@Override
	public PendingMaintenanceSchedule acceptPendingSchedule(Long id) {
		PendingMaintenanceSchedule sched = repo.findOne(id);
		List<PendingMaintenanceSchedule> scheds = repo.findByRepairReportId(sched.getRepairReportId());
		for(PendingMaintenanceSchedule s : scheds) {
			s.setScheduleStatus(ScheduleStatus.DECLINED);
		}
		repo.save(scheds);
		sched.setScheduleStatus(ScheduleStatus.ACCEPTED);
		return repo.save(sched);
	}

	@Override
	public PendingMaintenanceSchedule declinePendingSchedule(Long id) {
		PendingMaintenanceSchedule sched = repo.findOne(id);
		sched.setScheduleStatus(ScheduleStatus.DECLINED);
		return repo.save(sched);
	}

	@Override
	public boolean doesReportHaveAcceptedSchedule(Long reportId) {
		List<PendingMaintenanceSchedule> scheds = repo.findByRepairReportId(reportId);
		for(PendingMaintenanceSchedule sched : scheds) {
			if(sched.getScheduleStatus().equals(ScheduleStatus.ACCEPTED)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<PendingMaintenanceSchedule> getAllForReportAndSchedulerType(Long reportId, SchedulerType type) {
		List<PendingMaintenanceSchedule> scheds = repo.findByRepairReportId(reportId);
		List<PendingMaintenanceSchedule> filtered = new ArrayList<>();
		for(PendingMaintenanceSchedule sched : scheds) {
			if(sched.getSchedulerType().equals(type)) {
				filtered.add(sched);
			}
		}
		return filtered;
	}

	@Override
	public Long getRepairReportForId(Long id) {
		PendingMaintenanceSchedule sched = repo.findOne(id);
		if(sched == null) {
			return new Long(-1);
		} else {
			return sched.getRepairReportId();
		}
	}

	@Override
	public boolean doesItemExist(Long id) {
		return repo.findOne(id) != null;
	}

	@Override
	public PendingMaintenanceSchedule save(PendingMaintenanceSchedule sched) {
		return repo.save(sched);
	}

	@Override
	public PendingMaintenanceSchedule getForId(Long id) {
		return repo.findOne(id);
	}

	@Override
	public List<PendingMaintenanceSchedule> getAllForReport(Long reportId) {
		return repo.findByRepairReportId(reportId);
	}

}
